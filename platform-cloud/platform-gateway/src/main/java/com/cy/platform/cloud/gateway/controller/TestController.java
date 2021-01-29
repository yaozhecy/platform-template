package com.cy.platform.cloud.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @author develop
 */
@Controller
public class TestController {
    @GetMapping("/login")
    public Mono<String> hello(final Model model) {
        String path = "login";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @GetMapping("/index")
    public Mono<Integer> index() {
        Mono<Integer> mono = Mono.create(n -> n.success(1));
        mono.subscribe(System.out::println);

        return mono;
    }
}
