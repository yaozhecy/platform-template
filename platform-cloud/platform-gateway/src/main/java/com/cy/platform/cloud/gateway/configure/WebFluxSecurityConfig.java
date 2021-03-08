package com.cy.platform.cloud.gateway.configure;

import com.cy.platform.cloud.gateway.security.AuthenticationConverter;
import com.cy.platform.cloud.gateway.security.PlatformAuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.savedrequest.ServerRequestCache;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

/**
 * 前后端分离验证，实现思路:
 * 1.返回Json，不返回登录界面
 * 2.接受json数据进行验证
 *
 * @author develop
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    private final ServerRequestCache requestCache = new WebSessionServerRequestCache();
    private final ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //step 1:定义白名单
        http.authorizeExchange().pathMatchers("/template/login", "/dist/**").permitAll();
        //step 2:设置登录认证
        http.formLogin()
            //step 2.1.设置登录认证
            .authenticationManager(new PlatformAuthManager())
            //step 2.2.设置登录路径
            .requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/auth/login"))
            //step 2.3.认证成功
            .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                java.net.URI uri = java.net.URI.create("/template/dashboard");
                return this.requestCache.saveRequest(webFilterExchange.getExchange())
                    .then(this.redirectStrategy.sendRedirect(webFilterExchange.getExchange(), uri));
            })
            //step 2.4.认证失败
            .authenticationFailureHandler((webFilterExchange, authentication) -> {
                System.out.println("fal");
                return Mono.empty();
            })
            //step 2.4.认证切入点
            .authenticationEntryPoint((exchange, e) -> this.requestCache.saveRequest(exchange)
                .then(this.redirectStrategy.sendRedirect(exchange, java.net.URI.create("/template/login"))))
            //step 2.5.上下文管理
            .securityContextRepository(new WebSessionServerSecurityContextRepository());
        //step 4.认证
        http.authorizeExchange().anyExchange().authenticated();
        //step 5.添加认证过滤器
        http.addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);

        http.httpBasic().disable();
        http.csrf().disable();
        return http.build();
    }

    /**
     * 配置持有者校验过滤器
     *
     * @return 过滤器
     */
    private AuthenticationWebFilter bearerAuthenticationFilter() {
        //step 1：配置校验管理器
        ReactiveAuthenticationManager authManager = new PlatformAuthManager();
        //step 2：创建校验过滤器
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        //step 3：创建校验数据转换器
        bearerAuthenticationFilter.setServerAuthenticationConverter(new AuthenticationConverter());
        //step 4：配置过滤路径
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(
            ServerWebExchangeMatchers.pathMatchers("/api/**"));
        return bearerAuthenticationFilter;
    }
}
