package com.cy.platform.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BaseHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before()...");
        System.out.println("after()...");
        return "hello";
    }
}
