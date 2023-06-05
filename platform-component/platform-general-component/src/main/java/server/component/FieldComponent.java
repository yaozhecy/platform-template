package server.component;

import com.cy.platform.generation.server.constant.FieldLevelEnum;
import com.cy.platform.generation.server.domain.vo.field.FieldQueryParamVo;
import com.cy.platform.generation.server.domain.vo.field.SourceFieldVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段组件
 *
 * @author 56807
 */
@Component
public class FieldComponent {

    public List<SourceFieldVo> queryList(FieldQueryParamVo param) {
        List<SourceFieldVo> list = new ArrayList<>();
        if (param.getFieldLevel() == FieldLevelEnum.SYSTEM) {
            switch (param.getFieldClass()) {
                case SYSTEM:
                case PROJECT:
                case ENTITY:
                case FIELD:
            }
        } else if (param.getFieldLevel() == FieldLevelEnum.USERDEFINED) {

        }
        return list;
    }
}
