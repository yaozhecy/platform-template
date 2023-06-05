package server.core.model;

import com.cy.platform.generation.server.anno.FieldDesc;
import lombok.Data;

/**
 * 行列表
 *
 * @author 56807
 */
@Data
public class ColumnInfo {
    @FieldDesc("get名称")
    private String getName;
    /**
     * 列名称
     */
    @FieldDesc("列名称")
    private String columnName;
    /**
     * 数据类型
     */
    @FieldDesc("数据类型")
    private String dataType;
    /**
     * 属性名称
     */
    @FieldDesc("属性名称")
    private String attrName;
    /**
     * 属性类型
     */
    @FieldDesc("属性类型")
    private String attrType;
    /**
     * 中文名称
     */
    @FieldDesc("中文名称")
    private String chineseName;
    /**
     * 英文名称
     */
    @FieldDesc("英文名称")
    private String englishName;
    /**
     * 字段长度
     */
    @FieldDesc("字段长度")
    private String length = "22";
    /**
     * 是否必传
     */
    @FieldDesc("是否必传")
    private Integer required;
    /**
     * 是否为空
     */
    @FieldDesc("是否为空")
    private String notNull = "";
    /**
     * 描述
     */
    @FieldDesc("描述")
    private String comments;
}
