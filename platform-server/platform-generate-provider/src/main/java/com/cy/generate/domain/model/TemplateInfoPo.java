package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 数据模板
 *
 * @author 56807
 */
@Data
@TableName("TB_MB_MBXX")
public class TemplateInfoPo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 分组ID
     */
    @TableField("CFZID")
    private String groupId;
    /**
     * 模板名称
     */
    @TableField("CMBMC")
    private String templateName;
    /**
     * 模板路径
     */
    @TableField("CMBLJ")
    private String templatePath;
    /**
     * 是否默认:0 默认，1 非默认
     */
    @TableField("ISFMR")
    private Integer defaultType = 1;
    /**
     * 模板状态
     */
    @TableField("IMBZT")
    private Integer templateStatus;
    /**
     * 更新时间
     */
    @TableField("DCJRQ")
    private Date createTime;
}
