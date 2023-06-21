package com.cy.platform.gateway.common;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class TempTest {

    @Test
    public void test_reactive() {
        Flux<Integer> flux = Flux.fromArray(new Integer[]{1, 2, 3, 4});
        flux.filter(n -> n % 2 == 1).map(n -> n + 1).subscribe(System.out::println);
    }
}