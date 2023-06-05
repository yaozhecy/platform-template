package com.cy.platform.common.thread;

import com.cy.platform.common.thread.runable.TestObject;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadToolsTest {

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (ThreadToolsTest.class) {
                while (true) {
                }
            }
        }, "test_runable_1").start();

        new Thread(() -> {
            synchronized (ThreadToolsTest.class) {
                while (true) {
                }
            }
        }, "test_runable_2").start();
    }

    @Test
    public void test() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.before(date));
    }

    @Test
    public void test_runable() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(200000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "test_runable");
        thread.setDaemon(false);
        thread.start();
    }

    @Test
    public void test_wait() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join();
    }
}