package com.cy.generate.domain.vo.doc;

import lombok.Data;

/**
 * 文档实体
 *
 * @author 56807
 */
@Data
public class DocBodyVo {
    /**
     * 节点ID
     */
    private String nodeId;
    /**
     * 文件名称
     */
    private String name;
    /**
     * Body
     */
    private String body;
}
