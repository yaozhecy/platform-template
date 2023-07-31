package com.cy.platform.tools.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonSyncTools {

    @Async("main")
    public void sync() {
        log.info("hello thread");
    }
}
