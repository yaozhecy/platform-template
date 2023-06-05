package com.cy.platform.gateway.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.cy.platform.common.exception.ServiceException;
import com.cy.platform.common.http.R;
import com.cy.platform.gateway.security.bo.PlatformAuthentication;
import com.cy.platform.gateway.service.IAccountManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.*;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;

/**
 * 登录过滤器
 */
@Slf4j
public class LoginAuthFilter implements WebFilter {
    private final ServerWebExchangeMatcher requiresAuthenticationMatcher;
    private final ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    private final ServerAuthenticationFailureHandler authenticationFailureHandler;
    private final ServerSecurityContextRepository securityContextRepository;

    @Autowired
    private IAccountManage accountManage;

    public LoginAuthFilter() {
        authenticationSuccessHandler = new WebFilterChainServerAuthenticationSuccessHandler();
        authenticationFailureHandler = new ServerAuthenticationEntryPointFailureHandler(
                new HttpBasicServerAuthenticationEntryPoint());
        securityContextRepository = NoOpServerSecurityContextRepository.getInstance();
        requiresAuthenticationMatcher = ServerWebExchangeMatchers.pathMatchers("/manage/**");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //step 1、筛选对应路由
        return this.requiresAuthenticationMatcher.matches(exchange).filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                //step 3、解析认证信息
                .flatMap((matchResult) -> this.convert(exchange))
                //step 4、如果为空执行后续过滤器
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                //step 5、不为空执行认证操作
                .flatMap((token) -> authenticate(exchange, chain, token));
    }

    private Mono<Authentication> convert(ServerWebExchange exchange) {
        //step 1、获取授权头
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        //step 2、解析授权头
        Authentication authentication = PlatformAuthentication.build(authorization);
        return Mono.justOrEmpty(authentication);
    }

    private Mono<Void> authenticate(ServerWebExchange exchange, WebFilterChain chain, Authentication token) {
        //step 1、校验Token是否为空
        return Mono.justOrEmpty(token).switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceException("No provider found for " + token.getClass()))))
                //step 2、认证成功执行
                .flatMap((authentication) -> onAuthenticationSuccess(authentication, new WebFilterExchange(exchange, chain)))
                //step 3、异常执行
                .doOnError(AuthenticationException.class, (ex) -> log.warn(ex.getMessage(), ex));
    }

    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);

        return this.securityContextRepository.save(exchange, securityContext)
                .then(this.authenticationSuccessHandler.onAuthenticationSuccess(webFilterExchange, authentication))
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }
}
