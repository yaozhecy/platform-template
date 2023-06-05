package com.cy.platform.manage.server;

import com.cy.platform.model.redis.anno.EnablePlatformRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePlatformRedis("${test:1}")
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }
}
