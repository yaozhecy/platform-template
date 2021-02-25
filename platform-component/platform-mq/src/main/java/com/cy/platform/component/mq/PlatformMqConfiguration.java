package com.cy.platform.component.mq;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration
 *
 * @author develop
 */
@Configuration
@EnableConfigurationProperties
@ComponentScan("com.cy.platform.component.mq")
public class PlatformMqConfiguration {
}
