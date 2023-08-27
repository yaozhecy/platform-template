package com.cy.generate.common;

/**
 * 断言方法
 *
 * @author develop
 */
public final class ServiceAssert {

    public static void isTure(boolean flag, String message) {
        if (!flag) {
            throw new ServiceException(message);
        }
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new ServiceException(message);
        }
    }

    private ServiceAssert() {
        throw new UnsupportedOperationException();
    }
}
