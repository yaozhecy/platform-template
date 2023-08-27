package com.cy.generate.domain.vo.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 数据模板
 *
 * @author 56807
 */
@Data
public class TemplateInfoVo {
    /**
     * CID
     */
    private String id;
    /**
     * 分组ID
     */
    private String groupId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 是否启用：0 启用，1 停用
     */
    private Integer templateStatus = 1;
    /**
     * 模板内容
     */
    private String body;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
