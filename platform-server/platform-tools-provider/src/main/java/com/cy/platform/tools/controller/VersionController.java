package com.cy.platform.tools.controller;

import com.cy.platform.tools.thread.CommonSyncTools;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@Slf4j
public class VersionController {
    @Resource
    private CommonSyncTools commonSyncTools;

    @GetMapping("/info")
    public String info() {
        return "tools-rc-1.0";
    }

    @GetMapping("/redisinfo")
    public String queryRedisInfo() {
        commonSyncTools.sync();
        log.info("sync success");
        return "success";
    }
}