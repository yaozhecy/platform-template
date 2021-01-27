package com.cy.platform.starter.server.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.common.SessionHelper;
import com.cy.platform.component.datasource.MapperHelper;
import com.cy.platform.component.datasource.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础服务
 *
 * @author develop
 */
@Slf4j
@Service
public abstract class AbstractServiceImpl<M extends BaseMapper<U>, U extends BaseEntity<?>>
    extends ServiceImpl<M, U> {
    @Autowired
    protected MapperHelper mapperHelper;
    @Autowired
    protected SessionHelper sessionHelper;
}
