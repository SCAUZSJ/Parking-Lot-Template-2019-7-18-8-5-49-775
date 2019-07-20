package com.thoughtworks.parking_lot.controllerTest;

import com.thoughtworks.parking_lot.Entity.Car;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrder;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.Repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.Service.ParkingOrderService;
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

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingOrderService parkingOrderService;
    @Test
    public void should_return_201_when_add_a_parking_order() throws  Exception {
        //Given
        Car car = new Car("C001");
        ParkingOrder parkingOrder = new ParkingOrder("停车场1",car.getCarNum());
        String objectJson = new JSONObject(parkingOrder).toString();
//        when+then
        this.mockMvc.perform(post("/parkingOrders").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isCreated());
    }
    @Test
    public void should_update_order_status_when_fetch_a_car() throws  Exception {
        //Given
        Car car = new Car("C001");
        ParkingOrder parkingOrder = new ParkingOrder("一号停车场",car.getCarNum());
        parkingOrder.setStatus("ON");
        parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
        String id = parkingOrderRepository.saveAndFlush(parkingOrder).getOrderId();
        //when
        ParkingOrder parkingOrderNew = parkingOrderRepository.findById(id).get();
        String objectJson = new JSONObject(parkingOrderNew).toString();
        this.mockMvc.perform(put("/parkingOrders/"+parkingOrderNew.getOrderId()).contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isOk());
        //then
        Assertions.assertEquals("OFF",parkingOrderRepository.findById(id).get().getStatus());
    }

    @Test
    public void should_return_error_msg_when_parking_car_to_a_full_parking_lot() throws Exception{

        //Given
        ParkingLot parkingLot = parkingLotRepository.save(new ParkingLot("一号停车场",1,"A区"));
        parkingOrderService.add(new ParkingOrder("一号停车场","C001"));
        //when
        ParkingOrder parkingOrder = new ParkingOrder("一号停车场","C002");
        String objectJson = new JSONObject(parkingOrder).toString();
        String returnContent = this.mockMvc.perform(post("/parkingOrders").contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        //
        Assertions.assertEquals(true,returnContent.contains("停车场已经满了"));

    }

}
