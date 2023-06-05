package com.cy.platform.manage.domain.vo;

import lombok.Data;

/**
 * 账号登录参数
 */
@Data
public class AccountLoginParams {
    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 账号编码
     */
    private String accountPwd;
}
