package com.cy.platform.server.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.platform.component.datasource.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author cy
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoEntity extends BaseEntity<UserInfoEntity> {
    private static final long serialVersionUID = 1L;

    @TableField("auth_id")
    private Long authId;

    @TableField("user_name")
    private String userName;

    @TableField("sex")
    private String sex;

    @TableField("age")
    private String age;

    @TableField("telephone")
    private String telephone;

    @TableField("email")
    private String email;
}
