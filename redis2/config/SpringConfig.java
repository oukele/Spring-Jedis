package com.oukele.redis2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ComponentScan("com.oukele.redis2")
public class SpringConfig {

    @Bean
    RedisConnectionFactory redisFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    StringRedisTemplate redisTemplate(){
        return new StringRedisTemplate(redisFactory());
    }


}
