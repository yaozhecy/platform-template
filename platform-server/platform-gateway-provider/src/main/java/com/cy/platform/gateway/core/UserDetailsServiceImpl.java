package com.cy.platform.gateway.core;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 用户认证
 *
 * @author 56807
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.empty();
    }
}