package com.cy.platform.component.datasource.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 模板配置
 *
 * @author 56807
 */
@Data
@Component
@ConfigurationProperties(prefix = "platform.cy.datasource.generator")
public class TemplateConfigure {
    /**
     * 作者
     */
    private String author = "cy";
    /**
     * 模块名称
     */
    private String modelName = "temp";
    /**
     * 根包名
     */
    private String parentPackage = "com.cy";
    /**
     * VO包名
     */
    private String voPackage = "vo";
    /**
     * 接口工程路径
     */
    private String interProjectPath = "";
    /**
     * 工程路径
     */
    private String projectPath = "";
    /**
     * mapper路径
     */
    private String mapperPath = "/src/main/resources/mapper/";
    /**
     * mapper路径
     */
    private String filePath = "/src/main/java";
    /**
     * service后缀
     */
    private String serviceSuffix = "Service";
    /**
     * entity后缀
     */
    private String entitySuffix = "Entity";
    /**
     * vo后缀
     */
    private String voSuffix = "Vo";
    /**
     * Entity父类
     */
    private String superEntityClass = "com.cy.starter.mybatis.base.BaseEntity";
    /**
     * mapper后缀
     */
    private String mapperSuffix = "Mapper";
    /**
     * mapper父类
     */
    private String superMapperClass = "com.cy.starter.mybatis.base.PlatformMapper";
    /**
     * Service接口父类
     */
    private String superServiceClass = "com.cy.starter.base.inter.BaseService";
    /**
     * Service实现接口父类
     */
    private String superServiceImplClass = "com.cy.starter.service.beans.service.impl.BaseServiceImpl";
}
