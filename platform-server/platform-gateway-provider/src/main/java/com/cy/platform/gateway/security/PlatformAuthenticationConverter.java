package com.cy.platform.gateway.security;

import com.cy.platform.gateway.security.bo.PlatformAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 数据过滤器
 *
 * @author 56807
 */
public class PlatformAuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        //step 1、获取授权头
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        //step 2、解析授权头
        Authentication authentication = PlatformAuthentication.build(authorization);
        return Mono.justOrEmpty(authentication);
    }
}