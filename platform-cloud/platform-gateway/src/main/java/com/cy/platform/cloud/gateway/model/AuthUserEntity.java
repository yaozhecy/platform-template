package com.cy.platform.cloud.gateway.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author cy
 * @since 2020-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_user")
public class AuthUserEntity extends BaseEntity<AuthUserEntity> {
    private static final long serialVersionUID = 1L;

    @TableField("login_name")
    private String loginName;

    @TableField("login_pwd")
    private String loginPwd;

    @TableField("enabled")
    private String enabled;

    @TableField("locked")
    private String locked;
}
