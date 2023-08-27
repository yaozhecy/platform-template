package com.cy.generate.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 返回结果
 *
 * @author 56807
 */
@Getter
@ApiModel("返回结果")
public class PublicResult<T> {
    @ApiModelProperty("结果编码")
    private int code;
    @ApiModelProperty("结果消息")
    private String msg;
    @ApiModelProperty("结果数据")
    private T data;

    public static <U> PublicResult<U> success() {
        PublicResult<U> result = new PublicResult<>();
        result.code = 200;
        result.msg = "操作成功";
        return result;
    }

    public static <U> PublicResult<U> success(U data) {
        PublicResult<U> result = new PublicResult<>();
        result.code = 200;
        result.data = data;
        return result;
    }

    public static <U> PublicResult<U> failure(String msg) {
        PublicResult<U> result = new PublicResult<>();
        result.code = 100;
        result.msg = msg;
        return result;
    }
}
