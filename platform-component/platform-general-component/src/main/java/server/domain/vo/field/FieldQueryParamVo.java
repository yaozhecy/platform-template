package server.domain.vo.field;

import com.cy.platform.generation.server.constant.FieldClassEnum;
import com.cy.platform.generation.server.constant.FieldLevelEnum;
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
