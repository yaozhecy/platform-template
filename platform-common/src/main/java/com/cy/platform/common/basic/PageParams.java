package com.cy.platform.common.basic;

import lombok.Data;

/**
 * 分页查询参数
 *
 * @author chenyang
 */
@Data
public abstract class PageParams {
    /**
     * 当前页
     */
    private Long current;
    /**
     * 每页大小
     */
    private Long size;
}
