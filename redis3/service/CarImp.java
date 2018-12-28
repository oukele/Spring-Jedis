package com.oukele.redis2.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oukele.redis2.dao.CarMapper;
import com.oukele.redis2.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.List;

@Service
public class CarImp {

    @Autowired
    private CarMapper carMapper;

    //json 序列化
    public List<Car> listCars() {
        //借助redis的高效能力，让redis作为数据的缓存

        //打开 redis
        Jedis jedis = new Jedis();
        //json 序列化
        Gson gson = new Gson();
        //判断 redis 中 有没有 这个 键值 有的话，直接取 redis 中的数据
        if (jedis.exists("cars")) {
            List<Car> cars = gson.fromJson(jedis.get("cars"), new TypeToken<List<Car>>() {}.getType());
            return  cars;
        }
        //去数据库中 查询
        List<Car> cars = carMapper.listCars();
        //存储到redis中
        jedis.set("cars",gson.toJson(cars));

        return cars;
    }

    // 字节 序列化
    public List<Car> listCars1() {
        Jedis jedis = new Jedis();//打开 redis

        if( jedis.exists("cars1") ){//
            try {
                //读取 redis 中的 数据
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jedis.get("cars1".getBytes()));
                //反序列化
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                List<Car> o1 = (List<Car>) objectInputStream.readObject();
                return o1;

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        List<Car> cars = carMapper.listCars();
        try {
            // 字节数组
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //序列化
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(cars);
            //储存在redis 中
            jedis.set("cars1".getBytes(), byteArrayOutputStream.toByteArray());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    //使用 spring 封装好的 缓存管理器
    @Cacheable("list3")
    public List<Car> listCars2(){
        return carMapper.listCars();
    }

    public void add(Car car) {
        this.carMapper.add(car);
    }

    public Car findByCatId(Integer id) {

        // 借助 redis 的高效能力 ，让 redis 作为数据的缓存

        return carMapper.findByCatId(id);
    }

}
