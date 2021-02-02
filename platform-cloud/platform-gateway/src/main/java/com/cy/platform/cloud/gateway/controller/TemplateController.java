package com.cy.platform.cloud.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * 模板Controller
 *
 * @author 56807
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/login")
    public Mono<String> hello(final Model model) {
        String path = "login";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @GetMapping("/dashboard")
    public Mono<String> dashboard(final Model model) {
        String path = "dashboard";
        return Mono.create(monoSink -> monoSink.success(path));
    }
}
