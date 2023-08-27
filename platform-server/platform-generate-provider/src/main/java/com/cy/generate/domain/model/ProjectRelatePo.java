package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.generate.core.constant.RelateTypeEnum;
import lombok.Data;

@Data
@TableName("TB_GC_GCSJK")
public class ProjectRelatePo {
    @TableId("CID")
    private String id;

    @TableField("ILX")
    private RelateTypeEnum type;

    @TableField("CGCID")
    private String projectId;

    @TableField("CGLID")
    private String relateId;
}
