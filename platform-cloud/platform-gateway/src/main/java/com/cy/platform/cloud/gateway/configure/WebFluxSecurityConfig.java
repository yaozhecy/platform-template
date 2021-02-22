package com.cy.platform.cloud.gateway.configure;

import com.cy.platform.cloud.gateway.security.AuthenticationConverter;
import com.cy.platform.cloud.gateway.security.AuthenticationSuccessHandler;
import com.cy.platform.cloud.gateway.security.PlatformAuthManager;
import com.cy.platform.cloud.gateway.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

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
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .pathMatchers("/login", "/")
            .authenticated()
            .and()
            .addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
            .authorizeExchange()
            .pathMatchers("/api/**")
            .authenticated()
            .and()
            .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    /**
     * 登录校验过滤器
     *
     * @return 过滤器
     */
    private AuthenticationWebFilter basicAuthenticationFilter() {
        //step 1:创建用户认证器
        UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(new UserDetailsServiceImpl());
        authManager.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        //step 2:创建校验过滤器，并设置用户认证器
        AuthenticationWebFilter basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
        //step 3:设置成功出来方式
        basicAuthenticationFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler());
        return basicAuthenticationFilter;
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
            ServerWebExchangeMatchers.pathMatchers("/**"));
        return bearerAuthenticationFilter;
    }
}
