package com.cy.platform.model.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 平台Redis配置类
 *
 * @author 56807
 */
@Data
@Component
@ConfigurationProperties(prefix = "platform.redis")
public class PlatformRedisProperties {
    private String mode = "single";

    private Integer database = 0;

    private String host = "localhost";

    private Integer port = 6379;

    private String password;

    private int timeout = 3000;

    private String master;

    private String redisNodes;

    private int shutTimeout = 100;

    private int maxActive = 200;

    private int maxIdle = 10;

    private int maxWait = 10000;

    private int minIdle = 5;
}
