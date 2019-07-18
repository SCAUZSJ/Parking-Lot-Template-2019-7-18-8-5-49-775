package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;


    @PostMapping(produces = {"application/json"})
    public ResponseEntity add(@RequestBody ParkingLot parkingLot) {
        if(!parkingLotService.add(parkingLot)){
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().build();
    }

}
