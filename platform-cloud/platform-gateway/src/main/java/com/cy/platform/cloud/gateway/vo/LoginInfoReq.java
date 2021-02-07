package com.cy.platform.cloud.gateway.vo;

import lombok.Data;

/**
 * 登录参数
 *
 * @author 56807
 */
@Data
public class LoginInfoReq {
    /**
     * 登录用户
     */
    private String loginUser;
    /**
     * 登录密码
     */
    private String password;
}
