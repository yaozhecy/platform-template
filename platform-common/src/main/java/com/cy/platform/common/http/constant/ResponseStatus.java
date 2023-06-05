package com.cy.platform.common.http.constant;

import com.cy.platform.common.http.R;

import java.io.Serializable;

/**
 * Response 返回状态
 *
 * @author 56807
 */
public enum ResponseStatus {
    /**
     * 缺省
     */
    DEFAULT(216, "缺省"),
    /**
     * 用户未登录
     */
    NOT_LOGIN(502, "用户未登录"),
    /**
     * 用户权限不足
     */
    INSUFFICIENT_AUTHORITY(502, "用户权限不足"),
    /**
     * 用户账户或密码错误
     */
    ERROR_PASSWORD(504, "用户账户或密码错误"),
    /**
     * 账户被禁用
     */
    UNAVAILABLE_USER(503, "账户被禁用"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE(504, "登录失败"),
    /**
     * 访问成功
     */
    SUCCESS(200, "访问成功"),
    /**
     * 访问失败
     */
    FAILURE(501, "访问失败");

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;

    /**
     * 返回默认消息
     *
     * @return 返回消息
     */
    public R responseRecord() {
        return new R(code, msg, null);
    }

    /**
     * 返回消息（带消息体）
     *
     * @param data 消息体
     * @return 返回消息
     */
    public R responseRecord(Serializable data) {
        return new R(code, msg, data);
    }
}
