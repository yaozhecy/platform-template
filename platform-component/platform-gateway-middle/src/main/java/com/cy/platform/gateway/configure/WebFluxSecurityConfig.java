package com.cy.platform.gateway.configure;

import com.cy.platform.gateway.security.filter.LoginAuthFilter;
import com.cy.platform.gateway.security.filter.TokenAuthFilter;
import com.cy.platform.gateway.service.IAccountManage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, IAccountManage accountManage) {
        //step 1、自定义认证过滤器
        http.addFilterBefore(new LoginAuthFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        //step 1、自定义校验过滤器
        http.addFilterAfter(new TokenAuthFilter(), SecurityWebFiltersOrder.AUTHORIZATION);
        //step 2、关闭内部认证、跨站请求伪造
        http.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable).csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
