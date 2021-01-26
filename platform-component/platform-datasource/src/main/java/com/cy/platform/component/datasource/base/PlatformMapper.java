package com.cy.platform.component.datasource.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * 自定制模板Mapper
 *
 * @param <T> T
 * @author develop
 */
public interface PlatformMapper<T> extends BaseMapper<T> {
    /**
     * 逻辑删除
     *
     * @param id 主键ID
     */
    void logicDelete(Serializable id);
}
