package com.cy.platform.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/version")
public class VersionController {
    @Value("${version:}")
    private String version;

    @GetMapping("/info")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World" + version);
    }
}
