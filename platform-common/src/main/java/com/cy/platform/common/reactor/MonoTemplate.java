package com.cy.platform.common.reactor;

import reactor.core.publisher.Flux;

public class MonoTemplate {
    public void test() {
        Flux.just("a;b;c;d;f").flatMap(n -> Flux.just(n.split(";")))
            .subscribe(System.out::println);
    }
}
