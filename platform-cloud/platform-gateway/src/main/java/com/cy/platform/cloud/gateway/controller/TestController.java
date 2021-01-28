package com.cy.platform.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author develop
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public Mono<Integer> index() {
        Mono<Integer> mono = Mono.create(n -> n.success(1));
        mono.subscribe(System.out::println);

        return mono;
    }
}
