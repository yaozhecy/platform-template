package com.cy.platform.model.db.core;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 统一基础PO类
 */
@Data
public abstract class BasePo {
    /**
     * 数据库唯一主键
     */
    @TableId("c_id")
    private String id;
}
