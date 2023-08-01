package com.cy.platform.gateway.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.cy.platform.common.http.R;
import com.cy.platform.gateway.security.bo.PlatformAuthentication;
import com.cy.platform.gateway.service.IAccountManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.util.context.Context;

import java.util.ArrayList;

/**
 * Token 认证过滤器
 */
@Slf4j
public class TokenAuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //step 1、基于上下文进行校验过滤
        return ReactiveSecurityContextHolder.getContext()
                //step 2、获取认证信息
                .filter((c) -> c.getAuthentication() != null)
                .map(SecurityContext::getAuthentication)
                //step 3、对认证信息进行校验
                .as(this::validate)
                //step 4、认证成功
                .doOnSuccess(n -> log.info("认证成功"))
                //后续跳转
                .switchIfEmpty(chain.filter(exchange))
                //认证失败，直接返回401
                .onErrorResume(AccessDeniedException.class, (ex) -> Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().add("Content-Type", "application/json");
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    //step 5、写入Response
                    response.writeAndFlushWith(Mono.just(ByteBufMono.just(response.bufferFactory()
                            .wrap(JSONObject.toJSONBytes(R.success()))))).subscribe();
                }));
    }

    private Mono<Void> validate(Mono<Authentication> authentication) {
        return authentication
                .map(n -> {
                    log.info("权限认证开始！！！");
                    return new AuthorityAuthorizationDecision(n.isAuthenticated(), new ArrayList<>());
                })
                .defaultIfEmpty(new AuthorityAuthorizationDecision(true, new ArrayList<>()))
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccessDeniedException("认证失败"))))
                .flatMap((decision) -> Mono.empty());
    }
}
