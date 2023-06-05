package server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.generation.server.constant.RelateTypeEnum;
import com.cy.platform.generation.server.domain.mapper.ProjectRelateMapper;
import com.cy.platform.generation.server.domain.mapper.ProjectSettingMapper;
import com.cy.platform.generation.server.domain.mapper.ProjectSimpleMapper;
import com.cy.platform.generation.server.domain.model.ProjectRelatePo;
import com.cy.platform.generation.server.domain.model.ProjectSettingPo;
import com.cy.platform.generation.server.domain.model.ProjectSimplePo;
import com.cy.platform.generation.server.domain.transfor.CommonTransfor;
import com.cy.platform.generation.server.domain.vo.project.ProjectInfoVo;
import com.cy.platform.generation.server.domain.vo.project.item.ProjectSimpleVo;
import com.cy.platform.generation.server.service.IProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程管理服务
 *
 * @author 56807
 */
@Service
public class ProjectManageServiceImpl extends ServiceImpl<ProjectSimpleMapper, ProjectSimplePo>
    implements IProjectManageService {
    @Autowired
    private ProjectSimpleMapper projectSimpleMapper;
    @Autowired
    private ProjectSettingMapper projectSettingMapper;
    @Autowired
    private ProjectRelateMapper projectRelateMapper;

    @Override
    public IPage<ProjectSimpleVo> page(Long current, Long size) {
        return baseMapper.selectPage(new Page<>(current, size), new LambdaQueryWrapper<>())
            .convert(CommonTransfor.INSTANCE::toVo);
    }

    @Override
    public String record(ProjectInfoVo vo) {
        //step 1、保存工程基本信息
        ProjectSimplePo simplePo = CommonTransfor.INSTANCE.toPo(vo.getSimpleInfo());
        simplePo.setCreateTime(LocalDateTime.now());
        projectSimpleMapper.insert(simplePo);

        //step 2、保存配置信息
        ProjectSettingPo projectSettingPo = CommonTransfor.INSTANCE.toPo(vo.getSettingInfo());
        projectSettingPo.setProjectId(simplePo.getId());
        projectSettingMapper.insert(projectSettingPo);

        //step 4、设置关联数据库
        List<ProjectRelatePo> list = new ArrayList<>();
        for (JSONObject jsonObject : vo.getDatasource()) {
            ProjectRelatePo relatePo = new ProjectRelatePo();
            relatePo.setType(RelateTypeEnum.DB);
            relatePo.setProjectId(simplePo.getId());
            relatePo.setRelateId(jsonObject.getString("id"));
            projectRelateMapper.insert(relatePo);
        }

        //step 5、设置关联模版
        for (JSONObject jsonObject : vo.getTemplates()) {
            ProjectRelatePo relatePo = new ProjectRelatePo();
            relatePo.setType(RelateTypeEnum.TEMPLATE);
            relatePo.setProjectId(simplePo.getId());
            relatePo.setRelateId(jsonObject.getString("id"));
            projectRelateMapper.insert(relatePo);
        }

        return simplePo.getId();
    }

    @Override
    public ProjectSimpleVo querySimple(String id) {
        ProjectSimplePo po = baseMapper.selectById(id);
        return CommonTransfor.INSTANCE.toVo(po);
    }

    @Override
    public void recordSimple(ProjectSimpleVo vo) {
        ProjectSimplePo po = CommonTransfor.INSTANCE.toPo(vo);
        baseMapper.insert(po);
    }

    @Override
    public Page<?> queryProjectDataSourceList(String projectId) {

        return null;
    }
}
