package com.cy.code.manage.domain.entity;

/**
 * 字段档案信息
 *
 * @author chenyang
 */
public class TbFieldInfo {
    private Long id;
    /**
     * 数据源记录
     */
    private Long tbDataSource;
    /**
     * 序号
     */

    private Integer sortIndex;
    /**
     * 中文名称
     */

    private String chineseName;
    /**
     * 库表名称
     */
    private String tableName;
    /**
     * 库表描述
     */
    private String tableDesc;
    /**
     * 实体名称
     */
    private String entityName;
    /**
     * 英文名称
     */
    private String englishName;
}
