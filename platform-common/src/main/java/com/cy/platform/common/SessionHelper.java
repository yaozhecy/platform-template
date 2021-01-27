package com.cy.platform.common;

public interface SessionHelper {
    /**
     * 获取UserId
     *
     * @return 用户ID
     */
    Long getUserId();

    /**
     * 获取用户名
     *
     * @return 用户名称
     */
    String getUserName();

    /**
     * 获取用户Token
     *
     * @return token值
     */
    String getToken();
}
