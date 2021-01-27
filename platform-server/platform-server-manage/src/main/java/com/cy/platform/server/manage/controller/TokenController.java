package com.cy.platform.server.manage.controller;

import com.cy.platform.server.manage.entity.UserInfoEntity;
import com.cy.platform.server.manage.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenController
 *
 * @author think-cy
 */
@RestController
@Api("TokenController")
public class TokenController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/save")
    @ApiOperation(value = "商品新增")
    public String save() {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfoService.register(userInfo);
        return "成功";
    }

    @GetMapping("/query")
    @ApiOperation(value = "商品新增")
    public String queryToken() {
        return "";
    }
}
