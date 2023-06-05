package com.cy.platform.manage.domain.vo.auth;

import lombok.Data;

import java.util.List;

@Data
public class RouteInfoVO {
    /**
     * 路径
     */
    private String path;
    /**
     * 界面主键
     */
    private String component;
    /**
     * 重定向地址
     */
    private String redirect;
    /**
     * 原数据
     */
    private RouteInfoMetaVO meta;
    /**
     * 子节点
     */
    private List<RouteInfoVO> children;
}
