package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingOrderRepository  extends JpaRepository<ParkingOrder, String> {

    List<ParkingOrder> findByParkingLotNameAndStatus(String parkingLotName,String status);
}
