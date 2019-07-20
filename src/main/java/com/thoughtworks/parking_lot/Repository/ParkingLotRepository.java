package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    ParkingLot findByName(String name);
}
