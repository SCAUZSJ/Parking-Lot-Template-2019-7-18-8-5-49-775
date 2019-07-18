package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

//    @Modifying
//    @Query("delete from parkinglot where id = :id")
//    void deleteByUUId(@Param("id") String id);
}
