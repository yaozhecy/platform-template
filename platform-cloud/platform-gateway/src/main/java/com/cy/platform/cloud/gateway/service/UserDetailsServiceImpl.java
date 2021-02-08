package com.cy.platform.cloud.gateway.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * @author develop
 */
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    @Override
    public Mono<UserDetails> findByUsername(String s) {
        System.out.println(s);
        return Mono.empty();
    }
}
