package com.cy.platform.core.single;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PlatformSingleProperties.class})
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class PlatformSingleAutoConfigure {
}
