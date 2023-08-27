package com.cy.generate.domain.vo.template;

import lombok.Data;

/**
 * 模板分组信息
 *
 * @author 56807
 */
@Data
public class TemplateGroupVo {
    /**
     * ID
     */
    private String id;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 模板类型
     */
    private Integer type;
}
