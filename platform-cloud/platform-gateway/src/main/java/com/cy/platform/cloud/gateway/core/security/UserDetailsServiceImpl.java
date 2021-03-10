package com.cy.platform.cloud.gateway.core.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cy.platform.cloud.gateway.core.constant.DeleteFlag;
import com.cy.platform.cloud.gateway.db.mapper.AuthUserMapper;
import com.cy.platform.cloud.gateway.db.model.AuthUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        LambdaQueryWrapper<AuthUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserEntity::getLoginName, username);
        queryWrapper.eq(AuthUserEntity::getDeleteFlag, DeleteFlag.NORMAL);
        return Mono.justOrEmpty(authUserMapper.selectOne(queryWrapper));
    }
}
