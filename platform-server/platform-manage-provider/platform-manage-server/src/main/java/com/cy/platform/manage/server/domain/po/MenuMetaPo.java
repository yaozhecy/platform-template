package com.cy.platform.manage.server.domain.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.platform.model.db.core.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_mng_menu_meta")
public class MenuMetaPo extends BasePo {
    /**
     * 标题
     */
    @TableField("c_title")
    private String title;
    /**
     * icon
     */
    @TableField("c_icon")
    private String icon;
    /**
     * 是否隐藏
     */
    @TableField("b_hidden")
    private boolean hidden;
    /**
     * 是否一直显示
     */
    @TableField("b_alwaysShow")
    private boolean alwaysShow;
    /**
     * 是否活跃
     */
    @TableField("b_keepAlive")
    private boolean keepAlive;
}
