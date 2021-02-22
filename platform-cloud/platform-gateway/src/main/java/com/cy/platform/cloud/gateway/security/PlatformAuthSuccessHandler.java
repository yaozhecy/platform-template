package com.cy.platform.cloud.gateway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 认证成功处理方法
 *
 * @author 56807
 */
@Component
public class PlatformAuthSuccessHandler implements ServerAuthenticationSuccessHandler {
    /**
     * 认证成功
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        exchange.getResponse()
                .getHeaders()
                .add(HttpHeaders.AUTHORIZATION, "test");
        return webFilterExchange.getChain().filter(exchange);
    }
}