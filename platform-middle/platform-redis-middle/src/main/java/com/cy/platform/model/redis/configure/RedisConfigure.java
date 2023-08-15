package com.cy.platform.model.redis.configure;

import com.cy.platform.common.exception.ServiceException;
import com.cy.platform.model.redis.PlatformRedisProperties;
import com.cy.platform.model.redis.constant.RedisConstant;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * redis配置文件
 *
 * @author 56807
 */
@Configuration
public class RedisConfigure {
    @Autowired
    private PlatformRedisProperties redisProperties;

    /**
     * 创建RedisTemplate
     *
     * @return RedisTemplate
     */
    @Bean({"platformRedisTemplate"})
    public RedisTemplate<String, String> stringRedisTemplate() {
        //step 1、创建RedisTemplate
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        //step 2、设置连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        //step 3、设置数据转换器
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        //step 4、属性生效
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 创建RedisTemplate
     *
     * @param redisConnectionFactory 连接工厂
     * @return RedisTemplate
     */
    @Bean({"platformRedisTemplate2"})
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //step 1、创建RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //step 2、设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //使用JackJson转换器转换Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        //设置JSON转换工具
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //设置数据转换器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 根据不同模式构建Redis配置
     *
     * @return Redis配置
     */
    private RedisConfiguration redisConfiguration() {
        return switch (redisProperties.getMode()) {
            /*单节点模式*/
            case RedisConstant.SINGLE_MODE -> getRedisStandaloneConfiguration();
            /*集群模式*/
            case RedisConstant.CLUSTERS_MODE -> getRedisClusterConfiguration();
            /*哨兵模式*/
            case RedisConstant.SENTINEL_MODE -> getRedisSentinelConfiguration();
            default -> throw new ServiceException("Redis 配置异常");
        };
    }

    /**
     * 构建单例模式
     *
     * @return Redis配置信息
     */
    private RedisConfiguration getRedisStandaloneConfiguration() {
        /*单例模式*/
        RedisStandaloneConfiguration redisStandaloneConfiguration
            = new RedisStandaloneConfiguration();
        //服务地址
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        //端口
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        //数据库
        if (null != redisProperties.getDatabase()) {
            redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        }
        //密码
        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        }
        return redisStandaloneConfiguration;
    }

    /**
     * 集群模式配置类配置信息
     *
     * @return 配置信息
     */
    private RedisConfiguration getRedisClusterConfiguration() {
        /*集群模式*/
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //添加集群节点
        Set<RedisNode> nodes = new HashSet<>();
        String[] hosts = redisProperties.getHost().split(",");
        for (String host : hosts) {
            String h = host.replaceAll("\\s", "").replaceAll("\n", "");
            String[] split = h.split(":");
            //拆分host的IP与端口
            String ip = split[0];
            String port = split[1];
            nodes.add(new RedisNode(ip, Integer.parseInt(port)));
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        //设置重定向次数
        redisClusterConfiguration.setMaxRedirects(3);
        //密码设置
        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            redisClusterConfiguration.setPassword(redisProperties.getPassword());
        }
        return redisClusterConfiguration;
    }

    /**
     * 哨兵模式配置信息
     *
     * @return 哨兵模式配置信息
     */
    private RedisConfiguration getRedisSentinelConfiguration() {
        //哨兵模式
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
        //配置Mater节点与其它节点
        if (null != redisProperties.getRedisNodes() && null != redisProperties.getMaster()) {
            String[] hosts = redisProperties.getRedisNodes().split(",");
            for (String host : hosts) {
                String[] item = host.split(":");
                String ip = item[0];
                String port = item[1];
                configuration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
            }
            configuration.setMaster(redisProperties.getMaster());
        }
        //数据库
        if (null != redisProperties.getDatabase()) {
            configuration.setDatabase(redisProperties.getDatabase());
        }
        //密码设置
        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        return configuration;
    }

    /**
     * 连接工厂配置
     * 描述：
     * 使用lettuce
     *
     * @return 连接工厂
     */
    private RedisConnectionFactory lettuceConnectionFactory() {
        //step 1、定义线程池大小
        GenericObjectPoolConfig<?> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxIdle(this.redisProperties.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(this.redisProperties.getMinIdle());
        genericObjectPoolConfig.setMaxTotal(this.redisProperties.getMaxActive());

        //step 2、连接池工厂
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration =
            LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
        return new LettuceConnectionFactory(redisConfiguration(), lettucePoolingClientConfiguration);
    }
}
