package com.cy.platform.cloud.gateway.controller;

import com.cy.platform.cloud.gateway.vo.LoginInfoReq;
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

    @PostMapping("/login")
    public Mono<String> login(Mono<LoginInfoReq> person) {
        String path = "dashboard";
        return Mono.create(monoSink -> monoSink.success(path));
    }
}
