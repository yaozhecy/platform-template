package com.cy.platform.common.reactor;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;

@Slf4j
class MonoTemplateTest {

    @Test
    void test_base() {
        MonoTemplate template = new MonoTemplate();
        template.test();
    }

    @Test
    void test_mono() {
        Mono<String> mono = Mono.just("test");
        mono.as(n -> n).doOnSuccess(n -> System.out.println(n)).subscribe(n -> System.out.println("success"));
    }

    @Test
    public void test_mono_then() {
        Mono.just("test")
            .then(Mono.just("then"))
            .subscribe(n -> System.out.println("subscribe:" + JSONObject.toJSONString(n)));
    }

    @Test
    public void test_mono_base() {
        //step 1、创建参数
        Mono<String> mono = Mono.fromCallable(() -> {
            System.out.println("fromCallable");
            return "test";
        });
        //step 2、基础操作
        mono
            .filter(m -> {
                System.out.println("filter：" + JSONObject.toJSONString(m));
                return true;
            })
            .flatMap(n -> {
                System.out.println("flatMap：" + JSONObject.toJSONString(n));
                return Mono.just(n);
            })
            .as(n -> {
                System.out.println("as1:" + JSONObject.toJSONString(n));
                n.filter(m -> {
                    System.out.println("as flatMap：" + JSONObject.toJSONString(m));
                    return true;
                }).flatMap(m -> {
                    System.out.println("as flatMap：" + JSONObject.toJSONString(m));
                    return Mono.just(n);
                }).subscribe();
                System.out.println("as2:" + JSONObject.toJSONString(n));
                return n;
            }).doOnSuccess(n -> System.out.println("doOnSuccess:" + JSONObject.toJSONString(n)));
    }

    @Test
    public void test_flux_base() {
        Flux.create(sink -> {
            sink.next("test");
            sink.next("test");
            sink.next("test");
            sink.complete();
        }).subscribe(n -> System.out.println("doOnSuccess:" + JSONObject.toJSONString(n)));
    }
}