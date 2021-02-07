package com.cy.platform.cloud.gateway.controller;

import com.cy.platform.cloud.gateway.service.AuthService;
import com.cy.platform.cloud.gateway.vo.AuthUserVo;
import com.cy.platform.cloud.gateway.vo.LoginInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * 认证接口
 *
 * @author 56807
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Mono<String> login(Mono<LoginInfoReq> person) {
        String path = "dashboard";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @PostMapping("/register")
    public Mono<String> register(Mono<AuthUserVo> authUser) {
        final String path = "register";
        authService.saveUser(authUser);
        return Mono.create(monoSink -> monoSink.success(path));
    }
}
