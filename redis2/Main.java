package com.oukele.redis2;

import com.oukele.redis2.config.SpringConfig;
import com.oukele.redis2.dao.CarDao;
import com.oukele.redis2.entity.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CarDao carDao = context.getBean(CarDao.class);
        carDao.add(new Car(1,"宝马",34.3));

        carDao.get();
    }
}
