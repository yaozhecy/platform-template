package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 树节点
 * @author chenyang
 */
@Data
@TableName("TB_WD_WDJD")
public class DocNodePo {
    /**
     * id
     */
    @TableId("CID")
    private String id;
    /**
     * 父级节点
     */
    @TableField("CFJD")
    private String superId;
    /**
     * 标签
     */
    @TableField("CJDBQ")
    private String label;
    /**
     * 节点类型：0 目录，1 叶节点
     */
    @TableField("IJDLX")
    private Integer type;
}
