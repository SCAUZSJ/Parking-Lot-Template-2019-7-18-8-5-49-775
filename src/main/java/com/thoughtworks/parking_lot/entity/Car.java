package com.thoughtworks.parking_lot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Car {

    private String carNum;

    public Car(){}

    public Car(String carNum) {
        this.carNum = carNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
