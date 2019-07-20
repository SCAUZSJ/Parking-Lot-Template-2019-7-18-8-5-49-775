package com.thoughtworks.parking_lot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Car {

    private Long carNum;

    public Car(){}

    public Car(Long carNum) {
        this.carNum = carNum;
    }

    public Long getCarNum() {
        return carNum;
    }

    public void setCarNum(Long carNum) {
        this.carNum = carNum;
    }
}
