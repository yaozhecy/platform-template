package com.cy.platform.cloud.gateway.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 数据过滤器
 *
 * @author 56807
 */
public class AuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken("", ""));
    }
}
