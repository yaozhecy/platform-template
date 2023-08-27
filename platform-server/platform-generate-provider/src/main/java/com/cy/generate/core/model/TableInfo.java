package com.cy.generate.core.model;

import com.cy.generate.anno.FieldDesc;
import lombok.Data;

import java.util.List;

/**
 * 表数据
 *
 * @author 56807
 */
@Data
public class TableInfo {
    /**
     * 表名
     */
    @FieldDesc("表名")
    private String tableName;
    /**
     * 类名称
     */
    @FieldDesc("类名称")
    private String className;
    /**
     * 英文名称
     */
    @FieldDesc("英文名称")
    private String englishName;
    /**
     * 备注
     */
    @FieldDesc("备注")
    private String comments;

    @FieldDesc("主键UUID")
    private String pkUuid;

    /**
     * 列信息
     */
    private List<ColumnInfo> columns;
}
