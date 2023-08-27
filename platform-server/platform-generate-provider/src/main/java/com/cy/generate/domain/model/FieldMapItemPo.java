package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 字段映射项参数
 *
 * @author 56807
 */
@Data
@TableName("TB_MB_ZDXX")
public class FieldMapItemPo {
    /**
     * 主键
     */
    @TableId("CID")
    private String id;
    /**
     * 表格主题
     */
    @TableField("CZDZT")
    private String tableTheme;
    /**
     * 字段编码
     */
    @TableField("CZDBM")
    private String fieldCode;
    /**
     * 字段名称
     */
    @TableField("CZDMC")
    private String fieldName;
    /**
     * 字段级别：表级（table），列级（column）
     */
    @TableField("CZDDJ")
    private String fieldLevel;
    /**
     * 原始字段编码
     */
    @TableField("CYSZD")
    private String orgFieldCode;
    /**
     * 转换类型：基础转换器，脚本转换器
     */
    @TableField("IZHLX")
    private Integer converterType;
    /**
     * 转换器
     */
    @TableField("CZHQ")
    private String converter;
    /**
     * 字段脚本
     */
    @TableField("CZDJB")
    private String fieldScript;
    /**
     * 创建时间
     */
    @TableField("DCJSJ")
    private Date createTime;
}
