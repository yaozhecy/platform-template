package com.cy.generate.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 代码生成参数
 *
 * @author 56807
 */
@Data
public class CodeParamsVo {

    private String ds;

    private List<String> tables;
}
