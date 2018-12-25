package com.oukele.redis2.dao;

import com.oukele.redis2.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;

/*
* 数据访问类
* */
@Repository
public class CarDao {

    @Autowired
    private RedisOperations redisOperations;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;//也可以使用接口

    /*
    * 添加
    * */
    public void add( Car car ){
        redisOperations.opsForValue().set("cars",car.toString());
    }

    /*
    * 获取
    * */
    public void get(){
        System.out.println(redisOperations.opsForValue().get("cars"));
    }

    // ..... 其他操作

}
