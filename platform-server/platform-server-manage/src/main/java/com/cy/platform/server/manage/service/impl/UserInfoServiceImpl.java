package com.cy.platform.server.manage.service.impl;

import com.cy.platform.server.manage.entity.UserInfoEntity;
import com.cy.platform.server.manage.mapper.UserInfoMapper;
import com.cy.platform.server.manage.service.UserInfoService;
import com.cy.platform.starter.server.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cy
 * @since 2020-06-11
 */
@Service
public class UserInfoServiceImpl extends AbstractServiceImpl<UserInfoMapper, UserInfoEntity>
    implements UserInfoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserInfoEntity userInfo) {
        userInfo.setUserName("yaozhe");
        baseMapper.insert(userInfo);
    }
}
