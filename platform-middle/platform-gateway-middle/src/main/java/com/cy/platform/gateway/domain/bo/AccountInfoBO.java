package com.cy.platform.gateway.domain.bo;

import lombok.Data;

/**
 * 业务模型:用户信息
 */
@Data
public class AccountInfoBO {
    /**
     * 账号编码
     */
    private String code;
    /**
     * 用户名
     */
    private String accountName;
    /**
     * 密码
     */
    private String passWord;
}
