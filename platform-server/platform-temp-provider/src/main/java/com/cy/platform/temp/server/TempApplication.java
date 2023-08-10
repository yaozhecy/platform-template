package com.cy.platform.temp.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TempApplication {
    public static void main(String[] args) {
        log.debug("开发开始");
        SpringApplication.run(TempApplication.class, args);
    }
}
