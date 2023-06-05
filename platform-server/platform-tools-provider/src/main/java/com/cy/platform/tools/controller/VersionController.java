package com.cy.platform.tools.controller;

import com.alibaba.fastjson2.JSONObject;
import com.cy.platform.model.redis.component.RedisHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/version")
public class VersionController {
    @Resource
    private RedisHelper redisHelper;

    @GetMapping("/info")
    public String info() {
        return "tools-rc-1.0";
    }

    @GetMapping("/redisinfo")
    public String queryRedisInfo() {
        return JSONObject.toJSONString(redisHelper.getInfo());
    }
}