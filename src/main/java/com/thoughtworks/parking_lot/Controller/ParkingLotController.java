package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
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
        parkingLotService.add(parkingLot);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        parkingLotService.deleteById(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping(params = {"page","pageSize"})
    public ResponseEntity findAll(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize){
        return ResponseEntity.ok().body(parkingLotService.findAllByPage(page,pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id){
        return ResponseEntity.ok().body(parkingLotService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody ParkingLot parkingLot){
        return ResponseEntity.ok().body(parkingLotService.update(id,parkingLot));
    }

}
