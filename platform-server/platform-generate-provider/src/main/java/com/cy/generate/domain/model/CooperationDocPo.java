package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 协作文档PO
 *
 * @author 56807
 */
@Data
@TableName("TB_XZ_WDJL")
public class CooperationDocPo {
    /**
     * 主键
     */
    @TableId("CID")
    private String id;

    /**
     * 当前记录
     */
    @TableField("CKEY")
    private String ckey;

    /**
     * 文档名称
     */
    @TableField("CWDMC")
    private String docName;

    /**
     * 创建时间
     */
    @TableField("CCJSJ")
    private String createTime;

    /**
     * 创建状态
     */
    @TableField("IZT")
    private Integer status;
}
