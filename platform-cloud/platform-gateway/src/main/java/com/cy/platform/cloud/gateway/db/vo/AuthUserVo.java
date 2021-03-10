package com.cy.platform.cloud.gateway.db.vo;

import lombok.Data;

/**
 * 认证用户
 *
 * @author develop
 */
@Data
public class AuthUserVo {
    /**
     * 登录用户
     */
    private String loginUser;
    /**
     * 登录密码
     */
    private String passWord;
}
