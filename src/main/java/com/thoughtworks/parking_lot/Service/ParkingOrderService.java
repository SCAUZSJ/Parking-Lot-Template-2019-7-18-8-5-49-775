package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Enum.BusinessErrorMsg;
import com.thoughtworks.parking_lot.Enum.DBErrorMsg;
import com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.BusinessException;
import com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.DBException;
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

    private final String LOCATION = "["+this.getClass()+"]";

    public void add(ParkingOrder parkingOrder) throws RuntimeException {
        List<ParkingOrder> parkingOrders = parkingOrderRepository.findByParkingLotNameAndStatus(parkingOrder.getParkingLotName(),"ON");
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingOrder.getParkingLotName());
        if(parkingOrders.size()>=parkingLot.getCapacity()){
            throw new BusinessException(this.LOCATION+BusinessErrorMsg.PARKING_LOT_FULL.getMessage());
        }
        try {
            parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
            parkingOrder.setStatus("ON");
            parkingOrderRepository.saveAndFlush(parkingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(this.LOCATION+ DBErrorMsg.DB_INSERT_ERROR.getMessage());
        }
    }

    public ParkingOrder fetchCar(String id, ParkingOrder parkingOrder) throws RuntimeException  {
        parkingOrder.setOrderId(id);
        parkingOrder.setEndTime(new Timestamp(System.currentTimeMillis()));
        parkingOrder.setStatus("OFF");
        ParkingOrder parkingOrderNew = null;
        try {
            parkingOrder = parkingOrderRepository.saveAndFlush(parkingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(this.LOCATION+ DBErrorMsg.DB_UPDATE_ERROR.getMessage());
        }
        return parkingOrderNew;
    }
}
