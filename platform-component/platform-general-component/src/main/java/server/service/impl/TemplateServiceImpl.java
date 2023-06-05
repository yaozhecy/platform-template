package server.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ResourceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.generation.server.domain.mapper.TemplateGroupMapper;
import com.cy.platform.generation.server.domain.mapper.TemplateInfoMapper;
import com.cy.platform.generation.server.domain.model.TemplateGroupPo;
import com.cy.platform.generation.server.domain.model.TemplateInfoPo;
import com.cy.platform.generation.server.domain.transfor.CommonTransfor;
import com.cy.platform.generation.server.domain.vo.template.TemplateGroupVo;
import com.cy.platform.generation.server.domain.vo.template.TemplateInfoVo;
import com.cy.platform.generation.server.service.ITemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板服务接口
 *
 * @author 56807
 */
@Component
public class TemplateServiceImpl extends ServiceImpl<TemplateGroupMapper, TemplateGroupPo> implements ITemplateService {
    @Autowired
    private TemplateInfoMapper templateInfoMapper;

    @Override
    public List<TemplateGroupVo> queryGroupInfo() {
        return baseMapper.selectList(new QueryWrapper<>()).stream().map(CommonTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }

    @Override
    public String addGroup(TemplateGroupVo vo) {
        TemplateGroupPo po = CommonTransfor.INSTANCE.toPo(vo);
        po.setType(1);
        baseMapper.insert(po);
        return po.getId();
    }

    @Override
    public List<TemplateInfoVo> queryInfoList(String groupId) {
        return templateInfoMapper.selectList(new LambdaQueryWrapper<TemplateInfoPo>().eq(TemplateInfoPo::getGroupId, groupId)).stream().map(CommonTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }

    @Override
    public String saveTemplate(TemplateInfoVo vo) {
        //step 1.生成模板文件
        String filePath = "tempfile" + File.separator + "template"
            + File.separator + IdWorker.getIdStr() + ".vm";
        FileWriter writer = new FileWriter(filePath);
        writer.write(vo.getBody());

        //step 2.保存模板信息
        TemplateInfoPo po = CommonTransfor.INSTANCE.toPo(vo);
        po.setTemplateStatus(0);
        po.setDefaultType(1);
        po.setCreateTime(new Date());
        po.setTemplatePath(filePath);
        if (StringUtils.isBlank(po.getId())) {
            templateInfoMapper.insert(po);
        } else {
            templateInfoMapper.updateById(po);
        }
        return po.getId();
    }

    @Override
    public TemplateInfoVo queryTemplate(String id) {
        TemplateInfoPo po = templateInfoMapper.selectById(id);
        TemplateInfoVo vo = new TemplateInfoVo();
        if (po.getDefaultType() == 0) {
            vo.setBody(ResourceUtil.readStr(po.getTemplatePath(), Charset.defaultCharset()));
        } else {
            vo.setBody(FileUtil.readString(po.getTemplatePath(), Charset.defaultCharset()));
        }
        return vo;
    }
}
