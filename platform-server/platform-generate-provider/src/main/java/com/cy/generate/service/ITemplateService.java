package com.cy.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.TemplateGroupPo;
import com.cy.generate.domain.vo.template.TemplateGroupVo;
import com.cy.generate.domain.vo.template.TemplateInfoVo;

import java.util.List;

/**
 * 模板服务接口
 *
 * @author 56807
 */
public interface ITemplateService extends IService<TemplateGroupPo> {
    /**
     * 查询分组信息
     *
     * @return 分组信息列表
     */
    List<TemplateGroupVo> queryGroupInfo();

    /**
     * 添加分组
     *
     * @param vo vo
     * @return 分组ID
     */
    String addGroup(TemplateGroupVo vo);

    /**
     * 查询模板信息列表
     *
     * @param groupId 分组ID
     * @return 模板信息列表
     */
    List<TemplateInfoVo> queryInfoList(String groupId);

    /**
     * 保存模板
     *
     * @param vo VO
     * @return 模板ID
     */
    String saveTemplate(TemplateInfoVo vo);

    /**
     * 查询模板
     *
     * @param id 模板ID
     * @return 查询结果
     */
    TemplateInfoVo queryTemplate(String id);
}
