package com.cy.generate.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.generate.domain.constant.DataSourceType;
import lombok.Data;

import java.util.Date;

/**
 * 数据源
 *
 * @author 56807
 */
@Data
@TableName(value = "TB_DB_SJY")
public class DataSourcePo {
    /**
     * CID
     */
    @TableId("CID")
    private String id;
    /**
     * 版本
     */
    @TableField("IBBH")
    private Integer version;
    /**
     * 数据库名称
     */
    @TableField("CSJKMC")
    private String name;
    /**
     * 数据库类型
     */
    @TableField(value = "IDBTYPE")
    private DataSourceType dbType;
    /**
     * 数据源名称
     */
    @TableField("CSJYMC")
    private String dbName;
    /**
     * 连接地址
     */
    @TableField("CURL")
    private String url;
    /**
     * 用户
     */
    @TableField(value = "CUSER", property = "account")
    private String user;
    /**
     * 密码
     */
    @TableField("CPSSWD")
    private String passwd;
    /**
     * 文件路径
     */
    @TableField("CWJLJ")
    private String filePath;
    /**
     * 创建时间
     */
    @TableField("DCJSJ")
    private Date createTime;

    public String getAccount() {
        return user;
    }
}
