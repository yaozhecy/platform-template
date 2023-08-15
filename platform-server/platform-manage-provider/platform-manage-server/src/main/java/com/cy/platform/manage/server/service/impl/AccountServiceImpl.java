package com.cy.platform.manage.server.service.impl;

import com.cy.platform.manage.domain.vo.AccountInfoVO;
import com.cy.platform.manage.domain.vo.AccountLoginParams;
import com.cy.platform.manage.domain.vo.LoginInfoVO;
import com.cy.platform.manage.server.domain.mapper.AccountMapper;
import com.cy.platform.manage.server.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        accountMapper.selectById("1");
        return null;
    }
}
