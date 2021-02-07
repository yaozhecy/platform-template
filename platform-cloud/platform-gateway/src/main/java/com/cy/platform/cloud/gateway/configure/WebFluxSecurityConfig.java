package com.cy.platform.cloud.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
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
    private static final String LOGIN_PATH = "/login";
    private static final String LOGOUT_PTAH = "/logout";

    @Bean
    public SecurityWebFilterChain initSecurityWebFilterChain(ServerHttpSecurity http) {
        //step 1:配置权限路径
        http.formLogin().loginPage("/template/login").and().authorizeExchange()
                .pathMatchers("/template/**").permitAll()
                .pathMatchers("/web/**").permitAll()
                .pathMatchers("/css/**").permitAll()
                .pathMatchers("/dist/**").permitAll();

        //step 2:配置认证代理器与Json返回
        http.exceptionHandling()
                //step 2.1:用户未登录认证
                .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/template/login"))
                //step 2.3:用户权限不足处理
                .accessDeniedHandler((exchange, ex) -> {
                    return Mono.empty();
                });

        http.csrf().disable();
        return http.build();
    }
}
