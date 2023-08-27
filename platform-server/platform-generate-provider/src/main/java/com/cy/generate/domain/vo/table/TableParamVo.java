package com.cy.generate.domain.vo.table;

import lombok.Data;

/**
 * 库表查询参数
 *
 * @author 56807
 */
@Data
public class TableParamVo {
    /**
     * 数据ID
     */
    private String id;
    /**
     * 库表名称
     */
    private String name;
    /**
     * 库表编码
     */
    private String tableCode;
}
