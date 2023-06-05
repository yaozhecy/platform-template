package com.cy.platform.manage.server.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.platform.model.db.core.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_man_menu_info")
public class MenuInfoPo extends BasePo {
    /**
     * 路径
     */
    @TableField("c_path")
    private String path;

    /**
     * 组件
     */
    @TableField("c_component")
    private String component;

    /**
     * 重定向地址
     */
    @TableField("c_redirect")
    private String redirect;
}
