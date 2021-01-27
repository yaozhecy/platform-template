package com.cy.platform.server.manage.service;

import com.cy.platform.server.manage.entity.UserInfoEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cy
 * @since 2020-06-11
 */
public interface UserInfoService {
    /**
     * 注册用户
     *
     * @param userInfo 用户信息
     */
    void register(UserInfoEntity userInfo);
}
