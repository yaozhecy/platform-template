package com.cy.platform.gateway.domain.vo;

import lombok.Data;

/**
 * VO:登录信息
 */
@Data
public class LoginInfoVO {
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 登录密码
     */
    private String passWord;
}
