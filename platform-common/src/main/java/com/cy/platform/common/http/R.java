package com.cy.platform.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * Response 消息
 *
 * @author 56807
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class R<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -8754146517299676600L;

    /**
     * 错误码
     */
    private int code = 200;
    /**
     * 消息
     */
    private String msg = "操作成功";
    /**
     * 数据
     */
    private T data;

    /**
     * 默认成功
     *
     * @return 返回信息
     */
    public static <U> R<U> success() {
        return new R<>();
    }

    /**
     * 返回成功带Message信息
     *
     * @return 返回信息
     */
    public static <U> R<U> success(U data) {
        R<U> r = new R<>();
        r.setData(data);
        return r;
    }

    /**
     * 返回失败并带msg信息
     *
     * @return 返回信息
     */
    public static <U> R<U> failure(String msg) {
        R<U> info = new R<>();
        info.setMsg(msg);
        return info;
    }
}
