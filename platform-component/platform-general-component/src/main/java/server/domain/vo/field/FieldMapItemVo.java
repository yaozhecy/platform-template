package server.domain.vo.field;

import lombok.Data;

import java.util.Date;

/**
 * 字段映射项参数
 *
 * @author 56807
 */
@Data
public class FieldMapItemVo {
    /**
     * ID
     */
    private String id;
    /**
     * 字段编码
     */
    private String fieldCode;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段级别：table，column
     */
    private String fieldLevel;
    /**
     * 原始字段编码
     */
    private String orgFieldCode;
    /**
     * 转换器类型：1.默认转换器，2.脚本转换器
     */
    private Integer converterType;
    /**
     * 转换器
     */
    private String converter;
    /**
     * 字段编码
     */
    private String fieldScript;
    /**
     * 创建时间
     */
    private Date createTime;

    public static final String FIELD_LEVEL_TABLE = "table";
    public static final String FIELD_LEVEL_COLUMN = "column";

    public static FieldMapItemVo build(String fieldLevel, String fieldCode) {
        FieldMapItemVo vo = new FieldMapItemVo();
        vo.setFieldLevel(fieldLevel);
        vo.setFieldCode(fieldCode);
        return vo;
    }
}
