package com.cy.generate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.ProjectSimplePo;
import com.cy.generate.domain.vo.project.ProjectInfoVo;
import com.cy.generate.domain.vo.project.item.ProjectSimpleVo;

/**
 * 工程管理类
 *
 * @author 56807
 */
public interface IProjectManageService extends IService<ProjectSimplePo> {
    /**
     * 保存概要信息
     *
     * @param vo VO
     */
    String record(ProjectInfoVo vo);

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size    每页大小
     * @return 查询结果
     */
    IPage<ProjectSimpleVo> page(Long current, Long size);

    /**
     * 查询概要信息
     *
     * @param id ID
     * @return 概要信息
     */
    ProjectSimpleVo querySimple(String id);

    /**
     * 保存概要信息
     *
     * @param vo VO
     */
    void recordSimple(ProjectSimpleVo vo);

    /**
     * 根据工程ID查询工程下数据源
     *
     * @param projectId 工程ID
     * @return 数据源
     */
    Page<?> queryProjectDataSourceList(String projectId);
}
