package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.generate.domain.vo.doc.DocParamVo;
import com.cy.generate.domain.vo.project.ProjectInfoVo;
import com.cy.generate.domain.vo.project.item.ProjectSimpleVo;
import com.cy.generate.service.IProjectManageService;
import com.cy.generate.service.impl.TempInfoServiceImpl;
import com.cy.platform.common.http.R;
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
    public R<IPage<ProjectSimpleVo>> queryList(@RequestParam("current") Long current,
        @RequestParam("size") Long size, @RequestBody DocParamVo paramVo) {
        return R.success(projectManageService.page(current, size));
    }

    /**
     * 基础配置记录
     *
     * @return 记录结果
     */
    @PostMapping("/record")
    public R<?> record(@RequestBody ProjectInfoVo projectInfo) {
        return R.success(projectManageService.record(projectInfo));
    }

    /**
     * 查询简要信息
     *
     * @return 简要信息
     */
    @GetMapping("/simple/info")
    public R<?> querySimple(@RequestParam String id) {
        return R.success();
    }

    /**
     * 记录简要信息
     *
     * @return 成功/失败
     */
    @PostMapping("/simple/record")
    public R<?> recordSimple(@RequestBody ProjectSimpleVo projectVo) {
        return R.success();
    }

    /**
     * 基础配置信息查询
     *
     * @return 基础配置信息
     */
    @PostMapping("/base/query")
    public R<?> queryBase() {
        return R.success();
    }

    /**
     * 数据源记录
     *
     * @return 记录结果
     */
    @PostMapping("/source/record")
    public R<?> recordSource() {
        return R.success();
    }

    /**
     * 模板记录
     *
     * @return 记录结果
     */
    @PostMapping("/template/record")
    public R<?> recordTemplate() {
        return R.success();
    }

    @GetMapping("/datasource/list")
    public R<?> queryProjectDataSourceList(String projectId) {
        return R.success();
    }

    @GetMapping("/test")
    private void test() {
        tempInfoService.test();
    }
}
