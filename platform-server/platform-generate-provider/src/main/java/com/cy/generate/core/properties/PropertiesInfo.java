package com.cy.generate.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性信息
 *
 * @author 56807
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.cy")
public class PropertiesInfo {
    /**
     * 基础包路径
     */
    private String basePackage;
    /**
     * 模块路径
     */
    private String moduleName;
}
