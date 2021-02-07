package com.cy.platform.cloud.gateway.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * @author develop
 */
public class UserDetailsService implements ReactiveUserDetailsService {
    @Override
    public Mono<UserDetails> findByUsername(String s) {
        return null;
    }
}
