package com.cy.platform.cloud.gateway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 校验成功处理器
 *
 * @author 56807
 */
@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        exchange.getResponse().getHeaders().add(HttpHeaders.AUTHORIZATION, getHttpAuthHeaderValue(authentication));
        return webFilterExchange.getChain().filter(exchange);
    }

    private static String getHttpAuthHeaderValue(Authentication authentication) {
        return String.join(" ", "Bearer", tokenFromAuthentication(authentication));
    }

    private static String tokenFromAuthentication(Authentication authentication) {
        return "";
    }
}
