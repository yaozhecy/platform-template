package com.cy.platform.cloud.gateway.service.impl;

import com.cy.platform.cloud.gateway.mapper.AuthUserMapper;
import com.cy.platform.cloud.gateway.model.AuthUserEntity;
import com.cy.platform.cloud.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 认证服务
 *
 * @author develop
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public Mono<AuthUserEntity> findById(String id) {
        return Mono.justOrEmpty(authUserMapper.selectById(id));
    }
}
