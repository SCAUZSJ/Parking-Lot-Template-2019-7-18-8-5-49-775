package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Enum.DBErrorMsg;
import com.thoughtworks.parking_lot.ExceptionHandler.Exceptions.DBException;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    private final String LOCATION = "["+this.getClass()+"]";

    public void add(ParkingLot parkingLot) throws RuntimeException{

        try {
            parkingLotRepository.saveAndFlush(parkingLot);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(this.LOCATION+ DBErrorMsg.DB_INSERT_ERROR.getMessage());
        }
    }
    public Page<ParkingLot> findAllByPage(int page,int pageSize){
        return parkingLotRepository.findAll(PageRequest.of(page-1,pageSize));
    }
    public List<ParkingLot> findAll(){
        return parkingLotRepository.findAll();
    }


    public void deleteById(String id) throws RuntimeException{
        try {
            parkingLotRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(this.LOCATION+ DBErrorMsg.DB_INSERT_ERROR.getMessage());
        }
    }
    public ParkingLot findById(String id) {
        return parkingLotRepository.findById(id).get();
    }

    public ParkingLot update(String id, ParkingLot parkingLot) throws RuntimeException{
        parkingLot.setId(id);
        ParkingLot parkingLotNew = null;
        try {
            parkingLotNew = parkingLotRepository.saveAndFlush(parkingLot);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(this.LOCATION+DBErrorMsg.DB_UPDATE_ERROR.getMessage());
        }
        return parkingLotNew;
    }
}
