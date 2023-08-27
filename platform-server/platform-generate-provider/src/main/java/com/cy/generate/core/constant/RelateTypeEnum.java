package com.cy.generate.core.constant;

import lombok.Getter;

/**
 * 字段类型
 *
 * @author 56807
 */
@Getter
public enum RelateTypeEnum {
    /**
     * 数据库
     */
    DB(0),
    /**
     * 模版
     */
    TEMPLATE(1);

    private final Integer code;

    RelateTypeEnum(Integer code) {
        this.code = code;
    }
}
