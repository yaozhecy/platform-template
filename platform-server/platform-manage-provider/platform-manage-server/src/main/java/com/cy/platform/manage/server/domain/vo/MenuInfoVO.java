package com.cy.platform.manage.server.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * VO：菜单权限
 */
@Data
public class MenuInfoVO {
    /**
     * 路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 重定向地址
     */
    private String redirect;
    /**
     * 元数据
     */
    private MetaVO meta;
    /**
     * 子节点
     */
    private List<MenuInfoVO> children;

    /**
     * 元数据
     */
    @Data
    public static class MetaVO {
        /**
         * 标题
         */
        private String title;
        /**
         * icon
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
         * 菜单角色权限
         */
        private List<String> roles;
        /**
         * 是否活跃
         */
        private boolean keepAlive;
    }
}
