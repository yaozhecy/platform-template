package server.configure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cy.platform.generation.server.common.CyEnumTypeHandler;
import com.cy.platform.generation.server.domain.constant.DataSourceType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页插件
 *
 * @author 56807
 */
@Configuration
@MapperScan("com.cy.generation.domain.mapper")
public class MybatisPlusConfig implements ApplicationRunner {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry()
            .register(DataSourceType.class, CyEnumTypeHandler.class);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}