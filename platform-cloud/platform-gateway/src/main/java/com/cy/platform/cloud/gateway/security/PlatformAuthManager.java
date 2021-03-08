package com.cy.platform.cloud.gateway.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Collection;

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
        Authentication authentication1 = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };

        return Mono.justOrEmpty(authentication1);
    }
}