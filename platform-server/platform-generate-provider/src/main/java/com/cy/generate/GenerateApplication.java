package com.cy.generate;

import com.cy.generate.common.CyEnumTypeHandler;
import com.cy.generate.domain.constant.DataSourceType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GenerateApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext =SpringApplication.run(GenerateApplication.class, args);

        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DataSourceType.class, CyEnumTypeHandler.class);
    }
}
