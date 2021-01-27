package com.cy.platform.starter.server.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger配置
 *
 * @author think-cy
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docker() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("platform")
            .enable(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.cy.platform.server.manage.controller"))
            .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("yaozhe", "个人链接：http://xxx.xxx.com/",
            "568074019@qq.com");
        return new ApiInfo(
            "平台模板",
            "J2EE 模板工程",
            "v1.0.1",
            "http://121.37.5.66:8888/login",
            contact,
            "Apach 2.0 ",
            "",
            new ArrayList<>()
        );
    }
}
