package com.cy.generate.controller;

import com.cy.generate.common.PublicResult;
import com.cy.generate.domain.vo.ColumnInfoVo;
import com.cy.generate.domain.vo.ColumnListVo;
import com.cy.generate.service.IColumnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 列Controller
 *
 * @author 56807
 */
@RestController
@RequestMapping("/column")
public class ColumnInfoController {
    @Autowired
    private IColumnInfoService columnInfoService;

    @GetMapping("/list")
    public PublicResult<List<ColumnInfoVo>> queryList(@RequestParam("id") String id) {
        return PublicResult.success(columnInfoService.queryColumnList(id));
    }

    @GetMapping("/info")
    public PublicResult<ColumnInfoVo> queryInfo(@RequestParam("id") String id) {
        return PublicResult.success(columnInfoService.queryColumnInfo(id));
    }

    @PostMapping("/update")
    public PublicResult<String> updateInfo(@RequestBody ColumnListVo list) {
        columnInfoService.updateColumnInfo(list);
        return PublicResult.success();
    }
}
