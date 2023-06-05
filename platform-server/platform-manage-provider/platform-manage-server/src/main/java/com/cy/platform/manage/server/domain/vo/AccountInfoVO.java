package com.cy.platform.manage.server.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * VO：账号信息
 */
@Data
public class AccountInfoVO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 角色列表
     */
    private List<String> roles;
    /**
     * 权限列表
     */
    private List<String> perms;
}
