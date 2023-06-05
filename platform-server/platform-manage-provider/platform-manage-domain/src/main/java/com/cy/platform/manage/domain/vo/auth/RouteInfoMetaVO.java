package com.cy.platform.manage.domain.vo.auth;

import java.util.List;

public class RouteInfoMetaVO {
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否隐藏
     */
    private boolean hidden;
    /**
     * 是否一直显示
     */
    private boolean alwaysShow;
    /**
     * 角色列表
     */
    private List<String> roles;
    /**
     * 是否激活
     */
    private boolean keepAlive;
}
