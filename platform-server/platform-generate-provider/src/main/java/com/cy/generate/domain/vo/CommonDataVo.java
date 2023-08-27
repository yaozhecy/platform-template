package com.cy.generate.domain.vo;

import lombok.Data;

/**
 * 通用数据
 *
 * @author 56807
 */
@Data
public class CommonDataVo {
    /**
     * ID
     */
    private String id;
    /**
     * 数据源ID
     */
    private String dataSourceId;
    /**
     * 工具包
     */
    private String commonPackage;
    /**
     * 包路径
     */
    private String packagePath;
    /**
     * 模块路径
     */
    private String moduleName;
}
