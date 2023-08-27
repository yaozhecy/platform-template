package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 工程配置信息
 *
 * @author 56807
 */
@Data
@TableName("TB_GC_GCPZXX")
public class ProjectSettingPo {

    @TableId("CID")
    private String id;

    @TableField("CGCID")
    private String projectId;

    @TableField("CMKMC")
    private String modelName;

    @TableField("CGJBLJ")
    private String toolsPackage;

    @TableField("CMKLJ")
    private String modelPackage;

    @TableField("CPOPKG")
    private String poPackage;

    @TableField("CMPPKG")
    private String mapperPackage;

    @TableField("CDTOPKG")
    private String dtoPackage;

    @TableField("CSVPKG")
    private String servicePackage;

    @TableField("CSVIPKG")
    private String serviceImplPackage;
}
