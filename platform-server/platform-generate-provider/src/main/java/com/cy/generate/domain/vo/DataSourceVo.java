package com.cy.generate.domain.vo;

import com.cy.generate.domain.constant.DataSourceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 数据源VO
 *
 * @author 56807
 */
@Data
@Accessors(chain = true)
@ApiModel("数据源")
public class DataSourceVo {
    /**
     * id
     */
    @ApiModelProperty("id")
    private String id;
    /**
     * 文件ID
     */
    @ApiModelProperty("文件ID")
    private String fileId;
    /**
     * 数据库名称
     */
    @ApiModelProperty("数据库名称")
    private String name;
    /**
     * 数据库类型
     */
    @ApiModelProperty("数据库类型")
    private DataSourceType dbType;
    /**
     * 数据源名称
     */
    @ApiModelProperty("数据源名称")
    private String dbName;
    /**
     * 数据源版本
     */
    @ApiModelProperty("数据源版本")
    private Integer version;
    /**
     * 连接地址
     */
    @ApiModelProperty("连接地址")
    private String url;
    /**
     * 用户
     */
    @ApiModelProperty("用户")
    private String user;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String passwd;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
     * 版本列表
     */
    private List<String> versions;
}
