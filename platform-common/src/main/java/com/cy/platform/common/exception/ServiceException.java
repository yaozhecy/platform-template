package com.cy.platform.common.exception;

/**
 * 服务异常
 *
 * @author 56807
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String err) {
        super(err);
    }
}
