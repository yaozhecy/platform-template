package com.cy.platform.manage.domain.vo;

import lombok.Data;

@Data
public class LoginInfoVO {
    /**
     * 认证Token
     */
    private String accessToken;

    /**
     * 刷新Token
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private String expires;
}
