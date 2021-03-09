package com.cy.platform.cloud.gateway.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 删除状态
 *
 * @author develop
 */
@Getter
public enum DeleteFlag {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 删除
     */
    DELETE(1);

    @EnumValue
    private final int code;

    DeleteFlag(int code) {
        this.code = code;
    }
}
