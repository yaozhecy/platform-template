package com.cy.platform.component.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration注解的类可以看作是能生产让Spring IoC容器管理的Bean实例的工厂
 * ConditionalOnClass某个class位于类路径上，才会实例化这个Bean
 * 如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，
 * 那么在IOC容器中是获取不到properties 配置文件转化的bean
 *
 * @author develop
 */
@Configuration
@EnableConfigurationProperties()
@ConditionalOnClass()
public class PlatformDataSourceConfiguration {

    /**
     * 启动mybatis分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
