package com.oukele.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.oukele.redis")
public class SpringConfig {

     @Bean
     RedisConnectionFactory redisFactory(){
         //JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
         return new JedisConnectionFactory();
     }

     @Bean
    RedisTemplate redisTemplate(){
         return new StringRedisTemplate(redisFactory());
     }

}
