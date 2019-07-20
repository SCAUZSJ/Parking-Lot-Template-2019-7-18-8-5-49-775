package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Enum.BusinessErrorMsg;
import com.thoughtworks.parking_lot.Enum.DBErrorMsg;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.Repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    private final String SERVER_NAME = "[ParkingOrder]";

    public void add(ParkingOrder parkingOrder)  {
        List<ParkingOrder> parkingOrders = parkingOrderRepository.findByParkingLotNameAndStatus(parkingOrder.getParkingLotName(),"ON");
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingOrder.getParkingLotName());
        if(parkingOrders.size()>=parkingLot.getCapacity()){
            throw new com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.BusinessException(BusinessErrorMsg.PARKING_LOT_FULL.getMessage());
        }
        try {
            parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
            parkingOrder.setStatus("ON");
            parkingOrderRepository.saveAndFlush(parkingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.DBException(this.SERVER_NAME+ DBErrorMsg.DB_INSERT_ERROR.getMessage());
        }
    }

    public ParkingOrder fetchCar(String id, ParkingOrder parkingOrder) {
        parkingOrder.setOrderId(id);
        parkingOrder.setEndTime(new Timestamp(System.currentTimeMillis()));
        parkingOrder.setStatus("OFF");
        ParkingOrder parkingOrderNew = null;
        try {
            parkingOrder = parkingOrderRepository.saveAndFlush(parkingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.DBException(this.SERVER_NAME+ DBErrorMsg.DB_UPDATE_ERROR.getMessage());
        }
        return parkingOrderNew;
    }
}
