package com.cy.platform.cloud.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cy.platform.cloud.gateway.mapper.AuthUserMapper;
import com.cy.platform.cloud.gateway.model.AuthUserEntity;
import com.cy.platform.cloud.gateway.service.AuthService;
import com.cy.platform.cloud.gateway.transform.CommonTransform;
import com.cy.platform.cloud.gateway.vo.AuthUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Override
    public Mono<Boolean> authUser(String loginName, String passWord) {
        LambdaQueryWrapper<AuthUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserEntity::getLoginName, loginName);
        List<AuthUserEntity> list = authUserMapper.selectList(queryWrapper);
        return Mono.just(list.stream().anyMatch(n -> n.getLoginPwd().equals(passWord)));
    }

    @Override
    public Mono<Boolean> saveUser(Mono<AuthUserVo> authUser) {
        authUser.subscribe(entity -> authUserMapper.insert(CommonTransform.MAPPER.toEntity(entity)));
        return Mono.justOrEmpty(true);
    }
}
