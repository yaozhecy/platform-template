package com.cy.platform.gateway.service.impl;

import com.cy.platform.gateway.domain.bo.AccountInfoBO;
import com.cy.platform.gateway.domain.mapper.AccountMapper;
import com.cy.platform.gateway.domain.po.AccountInfoPo;
import com.cy.platform.gateway.domain.vo.LoginInfoVO;
import com.cy.platform.gateway.security.bo.PlatformAuthentication;
import com.cy.platform.gateway.service.IAccountManage;
import com.cy.platform.common.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManageService implements IAccountManage {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public PlatformAuthentication authentication(String token) {
        return PlatformAuthentication.build(token);
    }

    @Override
    public String loginForToken(LoginInfoVO accountInfo) {
        return JWTUtils.getToken(n->{});
    }

    @Override
    public AccountInfoBO queryUserInfoByName(String accountName) {
        AccountInfoBO infoBO = new AccountInfoBO();
        infoBO.setAccountName("root");
        infoBO.setPassWord("123456");
        return infoBO;
    }

    @Override
    public String addAccount() {
        accountMapper.insert(new AccountInfoPo());
        return "null";
    }
}
