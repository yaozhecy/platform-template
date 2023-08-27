package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 56807
 */
@Data
@TableName("TB_EX_KBSX")
public class ExcelColumnPo {
    @TableId("CID")
    private String id;

    @TableField("CZID")
    private String excelId;

    @TableField("CZDMC")
    private String columnName;

    @TableField("CSJLX")
    private String dataType;

    @TableField("CYWMC")
    private String englishName;

    @TableField("CBTX")
    private String required;

    @TableField("CZDCD")
    private String length;

    @TableField("CZWMC")
    private String chineseName;

    @TableField("CSFZJ")
    private String pri;

    @TableField("CBZXX")
    private String comment;
}
