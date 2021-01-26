package com.cy.platform.component.datasource.plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充器
 * <p>
 * 2020/5/11:使用Session获取用户ID
 *
 * @author develop
 */
@Slf4j
@Component
public class PlatformMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", Long.class, "");
        this.strictInsertFill(metaObject, "createOn", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "deleteFlag", String.class, "0");
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
