package com.thoughtworks.parking_lot.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="parking_order")
public class ParkingOrder {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String orderId;

    @Column(name = "parking_lot_name")
    private String parkingLotName;

    @Column(name = "car_num",nullable = false)
    private String carNum;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time",nullable = true)
    private Timestamp endTime;

    @Column(name="status")
    private String status;

    public ParkingOrder(){}

    public ParkingOrder(String parkingLotName, String carNum) {
        this.parkingLotName = parkingLotName;
        this.carNum = carNum;
    }

    public ParkingOrder(String parkingLotName, String carNum, Timestamp startTime, String status) {
        this.parkingLotName = parkingLotName;
        this.carNum = carNum;
        this.startTime = startTime;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public long getStartTime() {
        return startTime.getTime();
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime.getTime();
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
