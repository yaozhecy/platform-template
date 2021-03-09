package com.cy.platform.cloud.gateway.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cy.platform.cloud.gateway.constant.DeleteFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体对象
 *
 * @author develop
 */
@EqualsAndHashCode(callSuper = false)
@Data
public abstract class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {
    @TableId(value = "id")
    private Long id;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_on")
    private LocalDateTime createOn;

    @TableField(value = "delete_flag")
    private DeleteFlag deleteFlag;
}
