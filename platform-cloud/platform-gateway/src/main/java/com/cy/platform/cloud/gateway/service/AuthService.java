package com.cy.platform.cloud.gateway.service;

import com.cy.platform.cloud.gateway.db.model.AuthUserEntity;
import com.cy.platform.cloud.gateway.db.vo.AuthUserVo;
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

    /**
     * 用户认证
     *
     * @param loginName 登录名称
     * @param passWord  密码
     * @return 查询结果
     */
    Mono<Boolean> authUser(String loginName, String passWord);

    /**
     * 保存用户
     *
     * @param authUser 用户信息
     * @return 成功，失败
     */
    Mono<Boolean> saveUser(Mono<AuthUserVo> authUser);
}
