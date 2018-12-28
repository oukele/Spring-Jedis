package com.oukele.redis2.dao;

import com.oukele.redis2.entity.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CarMapper {

    //使用注解
    @Select("select * from car")
    List<Car> listCars ();

    @Insert("insert into car(name,speed) values(#{name},#{speed})")
    void add (Car car);

    @Select("select * from car where id=#{id} ")
    Car findByCatId(Integer id);

}
