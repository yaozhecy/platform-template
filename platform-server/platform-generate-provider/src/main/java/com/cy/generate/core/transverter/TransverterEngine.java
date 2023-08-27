package com.cy.generate.core.transverter;

import com.cy.generate.core.transverter.script.MyScriptEngine;
import com.cy.generate.core.transverter.trans.EmptyTransverter;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 转换器引擎
 *
 * @author 56807
 */
@Component
public class TransverterEngine {
    private final Map<BaseTransverterEnum, ITransverter> transverterMap = new HashMap<>();
    @Autowired
    private MyScriptEngine myScriptEngine;

    public TransverterEngine() {
        transverterMap.put(BaseTransverterEnum.EMPTY_Transverter, new EmptyTransverter());
    }

    public String transition(FieldMapItemVo fieldMapItem, final String value) {
        //step 1.校验参数
        if (StringUtils.isBlank(value)) {
            return value;
        }
        //step 2.数据转换
        if (DEFAULT_TYPE.equals(fieldMapItem.getConverterType())) {
            return transition(fieldMapItem.getConverter(), value);
        } else if (SCRIPT_TYPE.equals(fieldMapItem.getConverterType())) {
            return myScriptEngine.eval(fieldMapItem.getFieldScript(), value);
        }
        return value;
    }

    private String transition(String transverterCode, String value) {
        ITransverter transverter = transverterMap.get(BaseTransverterEnum
                .getTransverter(transverterCode));
        if (transverter != null) {
            return transverter.transition(value);
        }
        return value;
    }

    /**
     * 默认类型
     */
    public static final Integer DEFAULT_TYPE = 1;
    /**
     * 脚本类型
     */
    public static final Integer SCRIPT_TYPE = 2;
}
