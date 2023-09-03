package com.cy.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.generate.domain.vo.TableInfoVo;
import com.cy.generate.domain.vo.TableListVo;
import com.cy.generate.domain.vo.table.TableParamVo;
import com.cy.generate.service.ITableInfoService;
import com.cy.platform.common.http.R;
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
    public R<IPage<TableInfoVo>> queryTablesPage(@RequestParam("current") int current,
                                                            @RequestParam("size") int size, @RequestBody TableParamVo param) {
        if (StringUtils.isBlank(param.getId())) {
            return R.success(new Page<>());
        }
        return R.success(tableInfoService.queryTablePage(current, size, param));
    }

    @GetMapping("/list")
    public R<List<TableInfoVo>> queryTables(@RequestParam("id") String id) {
        if (StringUtils.isBlank(id)) {
            return R.success(new ArrayList<>());
        }
        return R.success(tableInfoService.queryTableList(id));
    }

    @GetMapping("/info")
    public R<TableInfoVo> queryTableInfo(@RequestParam("id") String id) {
        return R.success(tableInfoService.queryTableInfo(id));
    }

    @PostMapping("/update")
    public R<String> update(@RequestBody TableListVo list) {
        tableInfoService.updateTableInfo(list);
        return R.success();
    }
}
