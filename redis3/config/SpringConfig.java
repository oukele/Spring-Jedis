package com.oukele.redis2.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ComponentScan( basePackages ="com.oukele.redis2")
public class SpringConfig {

    @Bean
    RedisConnectionFactory redisFactory(){//  连接 Redis
        return new JedisConnectionFactory();
    }

    @Bean
    StringRedisTemplate redisTemplate(){// 使用 spring 封装好的模板 需要 redis的连接
        return new StringRedisTemplate(redisFactory());
    }


}
