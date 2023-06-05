package server.constant;

import lombok.Getter;

/**
 * 字段类型
 *
 * @author 56807
 */
@Getter
public enum FieldClassEnum {
    /**
     * 系统级
     */
    SYSTEM(0),
    /**
     * 工程级
     */
    PROJECT(1),
    /**
     * 实体级
     */
    ENTITY(2),
    /**
     * 字段级
     */
    FIELD(3);

    private final Integer code;

    FieldClassEnum(Integer code) {
        this.code = code;
    }
}
