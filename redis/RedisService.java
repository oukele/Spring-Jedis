package com.oukele.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class RedisService {

    @Autowired
    private RedisConnectionFactory redisFactory;

    @Autowired
    private RedisOperations redisTemplate;

    public void testRedis(){
        RedisConnection connection = redisFactory.getConnection();
        byte[] bytes = connection.get("hello".getBytes());
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    public void testRedisTemplate(){
        Object hello = redisTemplate.opsForValue().get("hello");
        System.out.println(hello);
    }

}
