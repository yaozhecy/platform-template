package com.cy.platform.gateway.controller;

import com.cy.platform.common.http.R;
import com.cy.platform.gateway.domain.vo.LoginInfoVO;
import com.cy.platform.gateway.service.IAccountManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 用户认证接口
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAccountManage accountManage;

    @PostMapping("/login")
    public Mono<R<?>> login(@RequestBody LoginInfoVO loginInfo) {
        return Mono.just(R.success(accountManage.loginForToken(loginInfo)));
    }
}
