package com.cy.platform.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

public interface IRouteService {
    /**
     * 更新路由配置
     *
     * @param routeDefinition 路由定义
     */
    void update(RouteDefinition routeDefinition);

    /**
     * 添加路由配置
     *
     * @param routeDefinition 路由定义
     */
    void add(RouteDefinition routeDefinition);
}
