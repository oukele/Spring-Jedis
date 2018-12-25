package com.oukele.redis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
//        Boolean role_1 = jedis.exists("role_1");
//        System.out.println(role_1);
//        System.out.println(jedis.hgetAll("role_1"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RedisService service = context.getBean(RedisService.class);
        service.testRedis();
        service.testRedisTemplate();

    }
}
