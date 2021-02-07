package com.cy.platform.cloud.gateway.service;

import com.cy.platform.cloud.gateway.model.AuthUserEntity;
import reactor.core.publisher.Mono;

/**
 * 认证服务
 *
 * @author develop
 */
public interface AuthService {
    /**
     * 根据ID查询
     *
     * @param id id
     * @return 查询结果
     */
    Mono<AuthUserEntity> findById(String id);
}
