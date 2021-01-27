package com.cy.platform.starter.server.handler;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return "1";
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public Object handleValidationException(ValidationException e) {
        return "2";
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        return "3";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handlerNoFoundException(Exception e) {
        return "4";
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Object handleDuplicateKeyException(DuplicateKeyException e) {
        return "5";
    }
}
