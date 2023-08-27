package com.cy.generate.domain.vo.field;

import com.cy.generate.core.constant.FieldClassEnum;
import com.cy.generate.core.constant.FieldLevelEnum;
import lombok.Data;

/**
 * 字段查询
 *
 * @author 56807
 */
@Data
public class FieldQueryParamVo {
    /**
     * 字段类别
     */
    private FieldClassEnum fieldClass;
    /**
     * 字段级别
     */
    private FieldLevelEnum fieldLevel;
}
