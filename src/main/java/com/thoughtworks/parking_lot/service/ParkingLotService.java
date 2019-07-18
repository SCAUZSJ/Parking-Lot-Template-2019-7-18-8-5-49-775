package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ParkingLot> findAllByPage(int page,int pageSize){
        return parkingLotRepository.findAll(PageRequest.of(page-1,pageSize));
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
    public ParkingLot findById(String id) {
        return parkingLotRepository.findById(id).get();
    }
}
