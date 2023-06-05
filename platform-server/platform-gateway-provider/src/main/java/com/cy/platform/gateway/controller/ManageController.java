package com.cy.platform.gateway.controller;

import com.cy.platform.common.collect.CollectTools;
import com.cy.platform.gateway.service.IAccountManage;
import com.cy.platform.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private IRouteService routeService;
    @Autowired
    private IAccountManage accountManage;

    @GetMapping("/account/add")
    public String addAccount() {
        log.info("addAccount");
        accountManage.addAccount();
        return "success";
    }

    @GetMapping("/add")
    public String add() {
        RouteDefinition definition = new RouteDefinition();
        definition.setId("manage");
        definition.setUri(URI.create("lb://manage-provider"));
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.addArg("Path", "/manage-provider/**");
        definition.setPredicates(CollectTools.newArrayList(predicateDefinition));
        routeService.add(definition);
        return "success";
    }
}
