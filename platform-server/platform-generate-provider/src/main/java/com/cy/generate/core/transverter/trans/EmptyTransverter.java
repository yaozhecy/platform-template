package com.cy.generate.core.transverter.trans;

import com.cy.generate.core.transverter.ITransverter;

/**
 * 空转换器
 *
 * @author 56807
 */
public class EmptyTransverter implements ITransverter {
    @Override
    public String transition(String source) {
        return source;
    }
}
