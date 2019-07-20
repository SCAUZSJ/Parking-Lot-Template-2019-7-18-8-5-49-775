package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingOrders")
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping(produces = {"application/json"})
    public ResponseEntity add(@RequestBody ParkingOrder parkingOrder) throws RuntimeException{
        parkingOrderService.add(parkingOrder);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody ParkingOrder parkingLot){
        return ResponseEntity.ok().body(parkingOrderService.fetchCar(id,parkingLot));
    }


}
