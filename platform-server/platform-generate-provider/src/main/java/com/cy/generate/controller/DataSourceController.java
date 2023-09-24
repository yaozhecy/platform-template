package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.cy.generate.domain.vo.CommonDataVo;
import com.cy.generate.domain.vo.DataSourceQueryParam;
import com.cy.generate.domain.vo.DataSourceVo;
import com.cy.generate.domain.vo.doc.DocParamVo;
import com.cy.generate.service.IDataSourceService;
import com.cy.platform.common.http.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 数据源服务器
 *
 * @author 56807
 */
@RestController
@RequestMapping("/ds")
@Slf4j
public class DataSourceController {
    @Autowired
    private IDataSourceService dataSourceService;

    @PostMapping("/list")
    @ApiOperation(value = "查询列表2", notes = "")
    public R<List<DataSourceVo>> queryList(@RequestBody DataSourceQueryParam param) {
        return R.success(dataSourceService.queryDataSourceList(param));
    }

    @PostMapping("/page")
    public R<IPage<DataSourceVo>> queryPage(@RequestBody DocParamVo param) {
        return R.success(dataSourceService.queryDataSourcePage(param.getCurrent(),
            param.getSize(), param));
    }

    @PutMapping
    public R<?> addDataSource(@RequestBody DataSourceVo dataSourceVo) {
        dataSourceService.addSourceInfo(dataSourceVo);
        return R.success();
    }

    @GetMapping("/info")
    public R<DataSourceVo> queryInfo(@RequestParam("id") String id) {
        return R.success(dataSourceService.queryInfo(id));
    }

    @PostMapping("/valid")
    public R<String> validDb(@RequestBody DataSourceVo vo) {
        dataSourceService.validDb(vo);
        return R.success();
    }

    @GetMapping("/delete")
    public R<String> delete(@RequestParam("id") String id) {
        dataSourceService.delete(id);
        return R.success();
    }

    @GetMapping("/common/data")
    public R<CommonDataVo> queryCommonData(@RequestParam("id") String id) {
        return R.success(dataSourceService.queryCommonDataVo(id));
    }

    @PostMapping("/common/data/update")
    public R<String> updateCommonData(@RequestBody CommonDataVo vo) {
        dataSourceService.updateCommonDataVo(vo);
        return R.success();
    }

    @PostMapping("/upload")
    @ResponseBody
    public R<String> uploadFileDir(MultipartFile file) throws Exception {
        String id = IdWorker.getIdStr();
        File tempDir = new File("tempfile");
        if (!tempDir.exists() && tempDir.mkdirs()) {
            return R.failure("文件创建失败");
        }

        File dir = new File("tempfile" + File.separator + id + "_" + file.getOriginalFilename());
        try (FileOutputStream outputStream = new FileOutputStream(dir)) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        }
        return R.success(id);
    }

    @GetMapping(value = "/download")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "模板.xls";
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("file/template.xlsx");
            servletOutputStream = response.getOutputStream();
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
}
