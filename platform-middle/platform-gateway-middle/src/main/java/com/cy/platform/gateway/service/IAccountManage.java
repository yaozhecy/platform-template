package com.cy.platform.gateway.service;

import com.cy.platform.gateway.domain.bo.AccountInfoBO;
import com.cy.platform.gateway.domain.vo.LoginInfoVO;
import com.cy.platform.gateway.security.bo.PlatformAuthentication;

public interface IAccountManage {
    /**
     * 基于Token认证
     *
     * @param token 认证信息
     * @return 认证结果
     */
    PlatformAuthentication authentication(String token);

    /**
     * 用户登录并返回Token
     *
     * @param loginInfo 登录信息
     * @return 认证Token
     */
    String loginForToken(LoginInfoVO loginInfo);

    /**
     * 根据账号名称查询账号信息
     *
     * @param accountName 账号名称
     * @return 账号信息
     */
    AccountInfoBO queryUserInfoByName(String accountName);

    String addAccount();
}
