package com.cy.platform.common.thread.runable;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class TestObject {
    final AtomicInteger orgLock = new AtomicInteger(0);
    private int index = 1;

    public void add() {
        orgLock.incrementAndGet();
        System.out.println(index + "开始锁");
        synchronized (orgLock) {
            System.out.println((index++) + "进入锁");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("异常");
            }
            System.out.println("退出");
        }
    }
}
