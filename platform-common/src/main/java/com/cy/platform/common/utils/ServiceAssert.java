package com.cy.platform.common.utils;

import com.cy.platform.common.collect.CollectTools;
import com.cy.platform.common.exception.ServiceException;

import java.util.Collection;

/**
 * 服务断言
 *
 * @author 56807
 */
public final class ServiceAssert {

    /**
     * 校验是否TRUE
     *
     * @param obj Obj
     * @param msg 异常信息
     */
    public static void isTrue(boolean obj, String msg) {
        if (!obj) {
            throw new ServiceException(msg);
        }
    }

    /**
     * 校验是否为NULL
     *
     * @param obj Obj
     * @param msg 异常信息
     */
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new ServiceException(msg);
        }
    }

    /**
     * 校验是否空
     *
     * @param obj Obj
     * @param msg 异常信息
     */
    public static void notEmpty(Collection<?> obj, String msg) {
        if (!CollectTools.isEmpty(obj)) {
            throw new ServiceException(msg);
        }
    }

    private ServiceAssert() {
        throw new UnsupportedOperationException();
    }
}
