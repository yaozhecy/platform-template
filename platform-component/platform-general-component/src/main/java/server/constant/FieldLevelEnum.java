package server.constant;

import lombok.Getter;

/**
 * 字段级别
 *
 * @author 56807
 */
@Getter
public enum FieldLevelEnum {
    /**
     * 系统级
     */
    SYSTEM(0),
    /**
     * 用户自定义
     */
    USERDEFINED(1);

    private final Integer code;

    FieldLevelEnum(Integer code) {
        this.code = code;
    }
}
