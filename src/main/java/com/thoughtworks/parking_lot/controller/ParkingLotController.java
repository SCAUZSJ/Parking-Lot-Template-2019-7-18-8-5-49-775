package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;


    @PostMapping(produces = {"application/json"})
    public ResponseEntity add(@RequestBody ParkingLot parkingLot) {
        if(!parkingLotService.add(parkingLot)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(id!=null){
            if(parkingLotService.deleteById(id)){
                return  ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping(params = {"page","pageSize"})
    public ResponseEntity findAll(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize){
        return ResponseEntity.ok().body(parkingLotService.findAllByPage(page,pageSize));
    }

}
