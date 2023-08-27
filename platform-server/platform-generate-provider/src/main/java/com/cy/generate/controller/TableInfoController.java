package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.generate.common.PublicResult;
import com.cy.generate.domain.vo.TableInfoVo;
import com.cy.generate.domain.vo.TableListVo;
import com.cy.generate.domain.vo.table.TableParamVo;
import com.cy.generate.service.ITableInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 库表服务
 *
 * @author 56807
 */
@RestController
@RequestMapping("/table")
public class TableInfoController {
    @Autowired
    private ITableInfoService tableInfoService;

    @PostMapping("/page")
    public PublicResult<IPage<TableInfoVo>> queryTablesPage(@RequestParam("current") int current,
                                                            @RequestParam("size") int size, @RequestBody TableParamVo param) {
        if (StringUtils.isBlank(param.getId())) {
            return PublicResult.success(new Page<>());
        }
        return PublicResult.success(tableInfoService.queryTablePage(current, size, param));
    }

    @GetMapping("/list")
    public PublicResult<List<TableInfoVo>> queryTables(@RequestParam("id") String id) {
        if (StringUtils.isBlank(id)) {
            return PublicResult.success(new ArrayList<>());
        }
        return PublicResult.success(tableInfoService.queryTableList(id));
    }

    @GetMapping("/info")
    public PublicResult<TableInfoVo> queryTableInfo(@RequestParam("id") String id) {
        return PublicResult.success(tableInfoService.queryTableInfo(id));
    }

    @PostMapping("/update")
    public PublicResult<String> update(@RequestBody TableListVo list) {
        tableInfoService.updateTableInfo(list);
        return PublicResult.success();
    }
}
