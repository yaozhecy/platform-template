package com.cy.platform.gateway.core.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class LogGatewayFilterFactory
    extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    /**
     * 构造函数，加载Config
     */
    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
        log.info("Loaded GatewayFilterFactory [Authorize]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("info");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("数据:" + config.getInfo());
            return chain.filter(exchange);
        };
    }

    @Data
    @AllArgsConstructor
    public static class Config {
        private String info;
    }
}
