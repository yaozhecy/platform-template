package com.cy.platform.cloud.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * 模板Controller
 *
 * @author 56807
 */
@Controller
@RequestMapping("/template")
@RefreshScope
public class TemplateController {

    @GetMapping("/login")
    public Mono<String> hello(final Model model) {
        String path = "login";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @PostMapping("/login")
    public Mono<String> login(final Model model) {
        String path = "dashboard";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @GetMapping("/register")
    public Mono<String> register(final Model model) {
        return Mono.create(monoSink -> monoSink.success("register"));
    }

    @GetMapping("/dashboard")
    public Mono<String> dashboard(final Model model) {
        String path = "/main/dashboard";
        return Mono.create(monoSink -> monoSink.success(path));
    }
}
