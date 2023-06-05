package com.cy.platform.manage.server.domain.vo;

import lombok.Data;

/**
 * VO:登录信息
 */
@Data
public class LoginInfoVO {
    /**
     * 认证token
     */
    private String accessToken;
    /**
     * 筛选token
     */
    private String refreshToken;
    /**
     * 过期时间
     */
    private String expires;

}
