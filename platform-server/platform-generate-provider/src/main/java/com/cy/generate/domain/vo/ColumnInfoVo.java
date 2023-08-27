package com.cy.generate.domain.vo;

import lombok.Data;

/**
 * 列表信息
 *
 * @author 56807
 */
@Data
public class ColumnInfoVo {
    /**
     * CID
     */
    private String id;
    /**
     * 是否主键
     */
    private Integer columnKey;
    /**
     * 库表名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 是否必传
     */
    private String required;
    /**
     * 数据长度
     */
    private String length;
    /**
     * 中文含义
     */
    private String chineseName;
    /**
     * 英文名称
     */
    private String englishName;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * 数据类型
     */
    private String dataType;
}
