package server.domain.vo.field;

import lombok.Data;

/**
 * 原字段数据
 *
 * @author 56807
 */
@Data
public class SourceFieldVo {
    /**
     * 字段编码
     */
    private String code;
    /**
     * 字段描述
     */
    private String desc;
    /**
     * 字段级别
     */
    private String level;

    public SourceFieldVo(String code, String desc, String level) {
        this.code = code;
        this.desc = desc;
        this.level = level;
    }

    public static SourceFieldVo build(String code, String desc, String level) {
        return new SourceFieldVo(code, desc, level);
    }

    public static SourceFieldVo build(String code, String level) {
        return new SourceFieldVo(code, "", level);
    }
}
