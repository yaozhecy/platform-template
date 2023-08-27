package com.cy.generate.domain.vo;

import lombok.Data;

/**
 * 表信息VO
 *
 * @author 56807
 */
@Data
public class TableInfoVo {
    /**
     * id
     */
    private String id;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 实体名称
     */
    private String entityName;
    /**
     * 英文名称
     */
    private String englishName;
    /**
     * 表描述
     */
    private String tableDesc;
}
