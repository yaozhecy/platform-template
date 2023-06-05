package com.cy.platform.manage.server.service.impl;

import com.cy.platform.manage.domain.vo.AccountInfoVO;
import com.cy.platform.manage.domain.vo.AccountLoginParams;
import com.cy.platform.manage.domain.vo.LoginInfoVO;
import com.cy.platform.manage.server.domain.mapper.AccountMapper;
import com.cy.platform.manage.server.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public String register(AccountInfoVO accountInfoVO) {
        return null;
    }

    @Override
    public LoginInfoVO login(AccountLoginParams accountLogin) {
        //虚假Token信息
        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setAccessToken("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6MiwidXNlcm5hbWUiOiJhZG1pbiIsImRlcHRJZCI6MiwiZGF0YVNjb3BlIjoxLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImV4cCI6MTY3Nzc3ODY3OX0.05EXTy4784tG7Pz1yVvyC6pY3ejSOW0vwgC9BYUH7Qc");
        return loginInfoVO;
    }

    @Override
    public AccountInfoVO getAccountInfo(String token) {
        AccountInfoVO accountInfoVO = new AccountInfoVO();
        accountInfoVO.setUserId("2");
        accountInfoVO.setNickname("系统管理员");
        accountInfoVO.setAvatar("https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif");
        accountInfoVO.setRoles(List.of("ADMIN"));
        accountInfoVO.setPerms(List.of("sys:user:edit", "sys:user:delete", "sys:user:add"));
        return accountInfoVO;
    }
}
