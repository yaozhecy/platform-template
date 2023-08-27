package com.cy.generate.core.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成属性
 *
 * @author 56807
 */
@Data
@Component
@ConfigurationProperties(prefix = "generation.properties")
public class GenerationProperties {
    /**
     * 文档地址
     */
    private String docUrl = "";
    /**
     * 下载地址
     */
    private String downloadPath = "";
    /**
     * OnlyOffice版本号
     */
    private String onlyOfficeVersion = "";
}
