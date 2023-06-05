package com.cy.platform.model.redis.anno;

import com.cy.platform.model.redis.RedisEnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisEnableAutoConfiguration.class})
public @interface EnablePlatformRedis {

    String value() default "";
}
