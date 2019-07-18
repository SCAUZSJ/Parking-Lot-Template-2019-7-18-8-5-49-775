package com.thoughtworks.parking_lot.controllerTest;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingLotService parkingLotService;
    @Test
    public void should_return_201_when_add_a_parking_lot() throws  Exception {

        ParkingLot parkingLot = new ParkingLot("停车场A",100,"某街道");
        String objectJson = new JSONObject(parkingLot).toString();
        this.mockMvc.perform(post("/parkingLots").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isCreated());
    }
    @Test
    public void should_return_200_when_delete_a_parking_lot() throws  Exception {

        List<ParkingLot> parkingLots = parkingLotService.findAll();
        int size = parkingLots.size();
        String uuid = parkingLots.get(0).getId();
        this.mockMvc.perform(delete("/parkingLots/"+uuid)).andExpect(status().isOk());
        Assertions.assertEquals(size-1,parkingLotService.findAll().size());
    }



}
