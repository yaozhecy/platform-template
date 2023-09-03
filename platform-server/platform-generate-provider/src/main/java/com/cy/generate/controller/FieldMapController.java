package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.generate.core.transverter.script.MyScriptEngine;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.field.QueryParamVo;
import com.cy.generate.domain.vo.field.ScriptCheckParamVo;
import com.cy.generate.domain.vo.field.SourceFieldVo;
import com.cy.generate.service.IFieldMapService;
import com.cy.platform.common.http.R;
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
    public R<List<SourceFieldVo>> getSourceField() {
        return R.success(fieldMapService.sourceFieldList());
    }

    @GetMapping("/source/basetransver")
    public R<?> getBaseTransver() {
        return R.success(fieldMapService.getBaseTransver());
    }

    @PostMapping("list")
    public R<?> list() {
        return R.success();
    }

    @PostMapping("/page")
    public R<IPage<FieldMapItemVo>> queryPage(Long current, Long size, @RequestBody QueryParamVo paramVo) {
        return R.success(fieldMapService.queryPage(current, size, paramVo));
    }

    @PostMapping("/add")
    public R<?> add(@RequestBody FieldMapItemVo vo) {
        return R.success(fieldMapService.add(vo));
    }

    @GetMapping("/delete")
    public R<?> delete(String id) {
        fieldMapService.delete(id);
        return R.success();
    }

    @PostMapping("/checkscript")
    public R<String> checkScript(@RequestBody ScriptCheckParamVo paramVo) {
        return R.success(scriptEngine.eval(paramVo.getScript(), paramVo.getValue()));
    }
}
