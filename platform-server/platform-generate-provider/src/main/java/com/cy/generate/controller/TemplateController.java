package com.cy.generate.controller;

import com.cy.generate.domain.transfor.CommonTransfor;
import com.cy.generate.domain.vo.template.TemplateGroupVo;
import com.cy.generate.domain.vo.template.TemplateInfoVo;
import com.cy.generate.service.ITemplateService;
import com.cy.platform.common.http.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义模板
 *
 * @author 56807
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private ITemplateService templateService;

    @GetMapping("/list")
    public R<List<TemplateGroupVo>> queryTables() {
        return R.success(templateService.list().stream()
                .map(CommonTransfor.INSTANCE::toVo).collect(Collectors.toList()));
    }

    @GetMapping("/group/list")
    public R<List<TemplateGroupVo>> queryGroupList() {
        return R.success(templateService.queryGroupInfo());
    }

    @PostMapping("/group/add")
    public R<String> addGroup(@RequestBody TemplateGroupVo vo) {
        return R.success(templateService.addGroup(vo));
    }

    @PostMapping("/group/edit")
    public R<String> editGroup(@RequestBody TemplateGroupVo vo) {
        return R.success();
    }

    @GetMapping("/info/list")
    public R<List<TemplateInfoVo>> queryInfoList(@RequestParam String groupId) {
        return R.success(templateService.queryInfoList(groupId));
    }

    @PostMapping("/info/save")
    public R<String> saveTemplate(@RequestBody TemplateInfoVo vo) {
        return R.success(templateService.saveTemplate(vo));
    }

    @GetMapping("/info/query")
    public R<TemplateInfoVo> saveTemplate(@RequestParam String id) {
        return R.success(templateService.queryTemplate(id));
    }
}
