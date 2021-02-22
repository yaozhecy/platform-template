package com.cy.platform.cloud.gateway.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * 平台认证服务
 *
 * @author 56807
 */
public class PlatformAuthManager implements ReactiveAuthenticationManager {
    
    /**
     * 认证接口
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication);
    }
}