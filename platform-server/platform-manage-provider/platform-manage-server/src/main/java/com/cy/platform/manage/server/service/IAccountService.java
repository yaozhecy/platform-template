package com.cy.platform.manage.server.service;

import com.cy.platform.manage.domain.vo.AccountInfoVO;
import com.cy.platform.manage.domain.vo.AccountLoginParams;
import com.cy.platform.manage.domain.vo.LoginInfoVO;

public interface IAccountService {

    String register(AccountInfoVO accountInfoVO);

    /**
     * 用户登录
     *
     * @param accountLogin 登录信息
     * @return Token信息
     */
    LoginInfoVO login(AccountLoginParams accountLogin);

    /**
     * 根据Token获取用户账号信息
     *
     * @param token token
     * @return 账号信息
     */
    AccountInfoVO getAccountInfo(String token);
}
