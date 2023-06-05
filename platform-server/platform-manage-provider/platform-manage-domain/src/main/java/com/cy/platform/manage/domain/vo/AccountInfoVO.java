package com.cy.platform.manage.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户账号信息
 */
@Data
public class AccountInfoVO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色
     */
    private List<String> roles;
    /**
     * 权限
     */
    private List<String> perms;
}
