package com.thoughtworks.parking_lot.controllerTest;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.Service.ParkingLotService;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    public void initData(){
        //初始化20条测试数据
        for(int i=0;i<20;i++){
            parkingLotRepository.save(new ParkingLot("停车场"+i,100+i*10,"地点"+i));
        }
    }
    @AfterEach
    public void clearData(){
        //清空被影响的测试数据
        parkingLotRepository.deleteAll();
    }


    @Test
    public void should_return_201_when_add_a_parking_lot() throws  Exception {
        //Given
        ParkingLot parkingLot = new ParkingLot("停车场A",100,"某街道");
        String objectJson = new JSONObject(parkingLot).toString();
        //when+then
        this.mockMvc.perform(post("/parkingLots").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isCreated());
    }
    @Test
    public void should_return_200_when_delete_a_parking_lot() throws  Exception {

        //Given
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        int size = parkingLots.size();
        String uuid = parkingLots.get(0).getId();
        //when
        this.mockMvc.perform(delete("/parkingLots/"+uuid)).andExpect(status().isOk());
        //then
        Assertions.assertEquals(size-1,parkingLotService.findAll().size());
    }
    @Test
    public void should_return_parking_lots_when_find_parking_lot_paging() throws  Exception {
        //Given
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        ParkingLot parkingLot = parkingLots.get(5);
        //when
        String content = this.mockMvc.perform(get("/parkingLots?page=2&pageSize=5")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        //then
        Assertions.assertEquals(5,json.get("size"));
        Assertions.assertEquals(parkingLot.getId(),json.getJSONArray("content").getJSONObject(0).getString("id"));
    }
    @Test
    public void should_return_parking_lots_info_when_find_parking_lot_paging_by_id() throws  Exception {
        //Given
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        String id = parkingLots.get(0).getId();
        //when
        String content = this.mockMvc.perform(get("/parkingLots/"+id)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        //then
        Assertions.assertEquals(id,json.get("id"));
    }

    @Test
    public void should_return_new_parking_lots_info_when_update_info() throws  Exception {
        //Given
        List<ParkingLot> parkingLots = parkingLotService.findAll();
        ParkingLot parkingLot = parkingLots.get(0);
        parkingLot.setCapacity(1000);
        String objectJson = new JSONObject(parkingLot).toString();
        //when
        String content = this.mockMvc.perform(put("/parkingLots/"+parkingLot.getId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(objectJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        //then
        Assertions.assertEquals(1000,json.get("capacity"));
    }





}
