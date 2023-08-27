package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.generate.common.PublicResult;
import com.cy.generate.domain.vo.doc.DocParamVo;
import com.cy.generate.domain.vo.project.ProjectInfoVo;
import com.cy.generate.domain.vo.project.item.ProjectSimpleVo;
import com.cy.generate.service.IProjectManageService;
import com.cy.generate.service.impl.TempInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 工程管理接口
 *
 * @author 56807
 */
@RestController
@RequestMapping("/project")
public class ProjectManageController {
    @Autowired
    private IProjectManageService projectManageService;
    @Autowired
    private TempInfoServiceImpl tempInfoService;

    @PostMapping("/page")
    public PublicResult<IPage<ProjectSimpleVo>> queryList(@RequestParam("current") Long current,
        @RequestParam("size") Long size, @RequestBody DocParamVo paramVo) {
        return PublicResult.success(projectManageService.page(current, size));
    }

    /**
     * 基础配置记录
     *
     * @return 记录结果
     */
    @PostMapping("/record")
    public PublicResult<?> record(@RequestBody ProjectInfoVo projectInfo) {
        return PublicResult.success(projectManageService.record(projectInfo));
    }

    /**
     * 查询简要信息
     *
     * @return 简要信息
     */
    @GetMapping("/simple/info")
    public PublicResult<?> querySimple(@RequestParam String id) {
        return PublicResult.success();
    }

    /**
     * 记录简要信息
     *
     * @return 成功/失败
     */
    @PostMapping("/simple/record")
    public PublicResult<?> recordSimple(@RequestBody ProjectSimpleVo projectVo) {
        return PublicResult.success();
    }

    /**
     * 基础配置信息查询
     *
     * @return 基础配置信息
     */
    @PostMapping("/base/query")
    public PublicResult<?> queryBase() {
        return PublicResult.success();
    }

    /**
     * 数据源记录
     *
     * @return 记录结果
     */
    @PostMapping("/source/record")
    public PublicResult<?> recordSource() {
        return PublicResult.success();
    }

    /**
     * 模板记录
     *
     * @return 记录结果
     */
    @PostMapping("/template/record")
    public PublicResult<?> recordTemplate() {
        return PublicResult.success();
    }

    @GetMapping("/datasource/list")
    public PublicResult<?> queryProjectDataSourceList(String projectId) {
        return PublicResult.success();
    }

    @GetMapping("/test")
    private void test() {
        tempInfoService.test();
    }
}
