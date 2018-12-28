package com.oukele.redis2.entity;

import java.io.Serializable;

public class Car implements Serializable {

    /*
     * 编号
     */
    private int id;
    /*
     * 车名
     */
    private String name;
    /*
     * 车速
     */
    private double speed;

    public Car(String name,double speed ){
        this.name = name;
        this.speed = speed;
    }

    public Car(int id, String name, double speed) {
        this.id = id;
        this.name = name;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                '}';
    }
}
