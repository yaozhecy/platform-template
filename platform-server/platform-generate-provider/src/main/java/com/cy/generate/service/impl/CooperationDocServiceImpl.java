package com.cy.generate.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.common.ServiceAssert;
import com.cy.generate.common.ServiceException;
import com.cy.generate.core.configure.GenerationProperties;
import com.cy.generate.domain.dto.ChangesHistory;
import com.cy.generate.domain.dto.Track;
import com.cy.generate.domain.mapper.CooHistoryMapper;
import com.cy.generate.domain.mapper.CooHistorySubMapper;
import com.cy.generate.domain.mapper.CooperationDocMapper;
import com.cy.generate.domain.model.CooHistoryPo;
import com.cy.generate.domain.model.CooHistorySubPo;
import com.cy.generate.domain.model.CooperationDocPo;
import com.cy.generate.domain.vo.*;
import com.cy.generate.service.ICooperationDocService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协作文档服务
 *
 * @author 56807
 */
@Service
public class CooperationDocServiceImpl extends ServiceImpl<CooperationDocMapper, CooperationDocPo>
        implements ICooperationDocService {
    private static final String DOC_TYPE = "doc";
    private static final String CHANGES_TYPE = "changes";
    @Autowired
    private GenerationProperties properties;
    @Autowired
    private CooHistoryMapper cooHistoryMapper;
    @Autowired
    private CooHistorySubMapper cooHistorySubMapper;

    @Override
    public List<CooperationDocVo> queryList() {
        return baseMapper.querDocList();
    }

    @Override
    public String saveFile(MultipartFile file) {
        //step 1.检查文件夹是否存在
        File tempDir = new File("tempfile");
        if (!tempDir.exists() && tempDir.mkdirs()) {
            throw new ServiceException("文件夹创建失败");
        }

        //step 2.数据记录
        String ckey = IdWorker.getIdStr();
        CooperationDocPo po = new CooperationDocPo();
        po.setCkey(ckey);
        po.setStatus(0);
        po.setDocName(file.getOriginalFilename());
        po.setCreateTime(DateUtil.now());
        baseMapper.insert(po);

        //step 3.保存文件
        String pathName = "tempfile" + File.separator + po.getId()
                + File.separator + file.getOriginalFilename();
        File dir = new File(pathName);
        try {
            FileUtil.writeBytes(file.getBytes(), dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //step 4.保存历史记录
        CooHistoryPo historyPo = new CooHistoryPo();
        historyPo.setDocCode(po.getId());
        historyPo.setCkey(ckey);
        historyPo.setStatus(-1);
        historyPo.setFiletype("docx");
        historyPo.setDocPath(pathName);
        historyPo.setDocUrl(properties.getDownloadPath() + "?type=doc&key=" + historyPo.getCkey());
        historyPo.setVersion(0);
        cooHistoryMapper.insert(historyPo);
        return po.getId();
    }

    @Override
    public void downloadDoc(HttpServletResponse response, String type, String key) {
        CooHistoryPo po = cooHistoryMapper.selectOne(new LambdaQueryWrapper<CooHistoryPo>()
                .eq(CooHistoryPo::getCkey, key));
        ServiceAssert.notNull(po, "未查询到文档信息");

        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String path, docName;
            if (DOC_TYPE.equals(type)) {
                path = po.getDocPath();
                docName = "temp.doc";
            } else if (CHANGES_TYPE.equals(type)) {
                path = po.getHistoryPath();
                docName = "changes.zip";
            } else {
                return;
            }

            response.setContentType("application/octet-stream");
            String encodeName = URLEncoder.encode(docName, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + encodeName + "\"; filename*=utf-8''" + encodeName);
            inputStream = FileUtil.getInputStream(new File(path));
            //servletOutputStream = response.getOutputStream();
            assert inputStream != null;
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void trackInfo(Track track) throws Exception {
        //保存历史文档
        if (track.getStatus() == 2 || track.getStatus() == 3) {
            //step 1.修改文件状态
            CooperationDocPo po = baseMapper.selectOne(new LambdaQueryWrapper<CooperationDocPo>()
                    .eq(CooperationDocPo::getCkey, track.getKey()));
            String newKey = IdWorker.getIdStr();
            po.setCkey(newKey);
            updateById(po);

            String docPath = "tempfile" + File.separator + po.getId() + File.separator
                    + newKey + File.separator + "temp.bak";
            //step 2.下载文件并缓存到服务器
            download(track.getUrl(), docPath);
            String hisPath = "";
            if (StringUtils.isNotBlank(track.getChangesurl())) {
                hisPath = "tempfile" + File.separator + po.getId() + File.separator
                        + newKey + File.separator + "tempHis.zip";
                download(track.getChangesurl(), hisPath);
            }

            //更新旧记录
            CooHistoryPo oldHistoryPo = cooHistoryMapper.selectOne(new LambdaQueryWrapper<CooHistoryPo>()
                    .eq(CooHistoryPo::getCkey, track.getKey()));

            //step 3.添加新的记录
            CooHistoryPo historyPo = new CooHistoryPo();
            historyPo.setDocCode(po.getId());
            historyPo.setCkey(newKey);
            historyPo.setStatus(track.getStatus());
            historyPo.setFiletype(track.getFiletype());
            if (track.getUsers() != null && !track.getUsers().isEmpty()) {
                String user = track.getUsers().get(0);
                historyPo.setUserId(user);
                historyPo.setUserName(user);
            }
            historyPo.setDocPath(docPath);
            historyPo.setDocUrl(properties.getDownloadPath() + "?type=doc&key=" + newKey);
            if (StringUtils.isNotBlank(hisPath)) {
                historyPo.setHistoryPath(hisPath);
                historyPo.setHistoryUrl(properties.getDownloadPath() + "?type=changes&key=" + newKey);
                historyPo.setServerVersion(properties.getOnlyOfficeVersion());
            }
            historyPo.setVersion(oldHistoryPo.getVersion() + 1);
            cooHistoryMapper.insert(historyPo);
            if (track.getHistory().getChanges() != null) {
                for (ChangesHistory change : track.getHistory().getChanges()) {
                    CooHistorySubPo subPo = new CooHistorySubPo();
                    subPo.setHistoryId(historyPo.getId());
                    subPo.setUpdateDate(change.getCreated());
                    subPo.setUserId(change.getUser().getId());
                    subPo.setUserName(change.getUser().getName());
                    cooHistorySubMapper.insert(subPo);
                }
            }
        }
    }

    @Override
    public List<HistoryVo> queryHistory(String docCode) {
        List<HistoryVo> historyVos = cooHistoryMapper.selectList(new LambdaQueryWrapper<CooHistoryPo>()
                .eq(CooHistoryPo::getDocCode, docCode).ne(CooHistoryPo::getStatus, -1)
                .orderByAsc(CooHistoryPo::getVersion)).stream().map(n -> {
            HistoryVo vo = new HistoryVo();
            vo.setId(n.getId());
            vo.setKey(n.getCkey());
            vo.setCreated(n.getUpdateDate());
            vo.setVersion(n.getVersion());
            vo.setServerVersion(n.getServerVersion());
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setId(n.getUserId());
            userInfoVo.setName(n.getUserName());
            vo.setUser(userInfoVo);
            return vo;
        }).collect(Collectors.toList());

        historyVos.forEach(n -> {
            List<CooHistorySubPo> temp = cooHistorySubMapper.selectList(
                    new LambdaQueryWrapper<CooHistorySubPo>()
                            .eq(CooHistorySubPo::getHistoryId, n.getId()));
            if (!temp.isEmpty()) {
                n.setChanges(temp.stream().map(m -> {
                    HistoryItemVo vo = new HistoryItemVo();
                    vo.setCreated(m.getUpdateDate());
                    UserInfoVo userInfoVo = new UserInfoVo();
                    userInfoVo.setId(m.getUserId());
                    userInfoVo.setName(m.getUserName());
                    vo.setUser(userInfoVo);
                    return vo;
                }).collect(Collectors.toList()));
            }
        });
        return historyVos;
    }

    @Override
    public HistoryDataVo queryHistoryData(String docCode, Integer version) {
        CooHistoryPo po = cooHistoryMapper.selectOne(new LambdaQueryWrapper<CooHistoryPo>()
                .eq(CooHistoryPo::getDocCode, docCode).eq(CooHistoryPo::getVersion, version));
        HistoryDataVo vo = new HistoryDataVo();
        vo.setKey(po.getCkey());
        vo.setUrl(po.getDocUrl());
        vo.setChangesUrl(po.getHistoryUrl());
        vo.setVersion(po.getVersion());

        po = cooHistoryMapper.selectOne(new LambdaQueryWrapper<CooHistoryPo>()
                .eq(CooHistoryPo::getDocCode, docCode).eq(CooHistoryPo::getVersion, version - 1));
        if (po != null) {
            vo.setPrevious(new LinkedHashMap<>());
            vo.getPrevious().put("key", po.getCkey());
            vo.getPrevious().put("url", po.getDocUrl());
        }
        return vo;
    }

    /**
     * 通过Url保存文件
     *
     * @param url  URL地址
     * @param path 文件路径
     * @throws Exception 异常
     */
    public void download(String url, String path) throws Exception {
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection)
                new URL(url).openConnection();
        FileUtil.writeFromStream(connection.getInputStream(), new File(path));
        connection.disconnect();
    }
}
