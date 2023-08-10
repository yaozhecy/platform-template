package com.cy.platform.temp.server.controller;

import com.cy.platform.common.http.R;
import com.cy.platform.temp.server.domain.TestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public R<List<TestInfo>> getTestInfo() {
        log.info("拉取数据");
        List<TestInfo> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(TestInfo.builder().id("1").name("test").desc("哈哈").build());
        }
        log.info("数据拉取完成");
        return R.success(list);
    }
}
