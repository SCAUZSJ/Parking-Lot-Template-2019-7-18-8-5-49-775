package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public boolean add(ParkingLot parkingLot){
        try {
            parkingLotRepository.saveAndFlush(parkingLot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }
    public List<ParkingLot> findAll(){
        return parkingLotRepository.findAll();
    }


    public boolean deleteById(String id) {
        try {
            parkingLotRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
