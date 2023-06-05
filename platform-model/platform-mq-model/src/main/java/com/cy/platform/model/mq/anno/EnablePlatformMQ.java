package com.cy.platform.model.mq.anno;

import com.cy.platform.model.mq.PlatformMQCoonfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({PlatformMQCoonfigure.class})
public @interface EnablePlatformMQ {

}
