package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 协作历史记录
 *
 * @author 56807
 */
@Data
@TableName("TB_XZ_LSJL")
public class CooHistoryPo {
    /**
     * ID
     */
    @TableId("CID")
    private String id;

    /**
     * 文档编码
     */
    @TableField("CWDBM")
    private String docCode;

    /**
     * 文档KEY
     */
    @TableField("CKEY")
    private String ckey;

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
     * 文档状态
     */
    @TableField("CZT")
    private Integer status;

    /**
     * 文件类型
     */
    @TableField("CWJLX")
    private String filetype;

    /**
     * 文档地址
     */
    @TableField("CWDDZ")
    private String docPath;

    /**
     * 文档URL
     */
    @TableField("CWDURL")
    private String docUrl;

    /**
     * 修改记录地址
     */
    @TableField("CXGDZ")
    private String historyPath;

    /**
     * 修改URL
     */
    @TableField("CXGURL")
    private String historyUrl;

    /**
     * 更新时间
     */
    @TableField("CXGRQ")
    private String updateDate;

    /**
     * 版本
     */
    @TableField("IBB")
    private Integer version;

    /**
     * 服务版本
     */
    @TableField("CFWBB")
    private String serverVersion;
}
