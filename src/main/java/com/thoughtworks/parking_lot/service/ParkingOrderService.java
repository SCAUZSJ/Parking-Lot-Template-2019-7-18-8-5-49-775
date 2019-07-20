package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ParkingOrderService {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    public boolean add(ParkingOrder parkingOrder) {
        try {
            parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
            parkingOrder.setStatus("ON");
            parkingOrderRepository.saveAndFlush(parkingOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public ParkingOrder fetchCar(String id, ParkingOrder parkingOrder) {
        parkingOrder.setOrderId(id);
        parkingOrder.setEndTime(new Timestamp(System.currentTimeMillis()));
        parkingOrder.setStatus("OFF");
        return parkingOrderRepository.saveAndFlush(parkingOrder);
    }
}
