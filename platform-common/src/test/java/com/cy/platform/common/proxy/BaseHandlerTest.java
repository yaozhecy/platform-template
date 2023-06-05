package com.cy.platform.common.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

public class BaseHandlerTest {

    @Test
    public void test() {
        IUser user = (IUser) Proxy.newProxyInstance(IUser.class.getClassLoader(),
            new Class[]{IUser.class}, new BaseHandler());
        user.test();
    }
}