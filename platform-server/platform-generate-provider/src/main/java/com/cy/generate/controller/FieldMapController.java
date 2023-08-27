package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.generate.common.PublicResult;
import com.cy.generate.core.transverter.script.MyScriptEngine;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.field.QueryParamVo;
import com.cy.generate.domain.vo.field.ScriptCheckParamVo;
import com.cy.generate.domain.vo.field.SourceFieldVo;
import com.cy.generate.service.IFieldMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字段映射服务接口
 *
 * @author 56807
 */
@RestController
@RequestMapping("/field/map")
public class FieldMapController {
    @Autowired
    private IFieldMapService fieldMapService;
    @Autowired
    private MyScriptEngine scriptEngine;

    @GetMapping("/source/list")
    public PublicResult<List<SourceFieldVo>> getSourceField() {
        return PublicResult.success(fieldMapService.sourceFieldList());
    }

    @GetMapping("/source/basetransver")
    public PublicResult<?> getBaseTransver() {
        return PublicResult.success(fieldMapService.getBaseTransver());
    }

    @PostMapping("list")
    public PublicResult<?> list() {
        return PublicResult.success();
    }

    @PostMapping("/page")
    public PublicResult<IPage<FieldMapItemVo>> queryPage(Long current, Long size, @RequestBody QueryParamVo paramVo) {
        return PublicResult.success(fieldMapService.queryPage(current, size, paramVo));
    }

    @PostMapping("/add")
    public PublicResult<?> add(@RequestBody FieldMapItemVo vo) {
        return PublicResult.success(fieldMapService.add(vo));
    }

    @GetMapping("/delete")
    public PublicResult<?> delete(String id) {
        fieldMapService.delete(id);
        return PublicResult.success();
    }

    @PostMapping("/checkscript")
    public PublicResult<String> checkScript(@RequestBody ScriptCheckParamVo paramVo) {
        return PublicResult.success(scriptEngine.eval(paramVo.getScript(), paramVo.getValue()));
    }
}
