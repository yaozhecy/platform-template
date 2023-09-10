package com.cy.generate.domain.vo.doc;

import com.cy.platform.common.basic.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文档参数
 *
 * @author 56807
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DocParamVo extends PageParams {
    /**
     * 文档名称
     */
    private String name;
}
