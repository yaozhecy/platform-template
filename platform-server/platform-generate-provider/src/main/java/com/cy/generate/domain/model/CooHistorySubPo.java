package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 协作历史子记录
 *
 * @author 56807
 */
@Data
@TableName("TB_XZ_LSJL2")
public class CooHistorySubPo {
    /**
     * ID
     */
    @TableId("CID")
    private String id;

    /**
     * 主记录ID
     */
    @TableField("CZJLID")
    private String historyId;

    /**
     * 用户ID
     */
    @TableField("CYHID")
    private String userId;

    /**
     * 用户名称
     */
    @TableField("CYHMC")
    private String userName;
    
    /**
     * 更新时间
     */
    @TableField("CXGRQ")
    private String updateDate;
}
