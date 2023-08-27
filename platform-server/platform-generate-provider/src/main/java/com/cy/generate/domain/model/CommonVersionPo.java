package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 通用数据版本管理
 *
 * @author 56807
 */
@Data
@TableName("TB_KZ_SJBB")
public class CommonVersionPo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 业务类型
     */
    @TableField("CYWLX")
    private String businessType;
    /**
     * 业务编码
     */
    @TableField("CYWBM")
    private String businessCode;
    /**
     * 版本号
     */
    @TableField("IBBH")
    private Integer version;
    /**
     * 当前版本
     */
    @TableField("BDQBB")
    private Byte currentVersion;
}
