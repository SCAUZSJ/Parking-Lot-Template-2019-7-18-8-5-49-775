package com.thoughtworks.parking_lot.controllerTest;

import com.thoughtworks.parking_lot.entity.Car;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
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
    private ParkingOrderService parkingOrderService;

    @Test
    public void should_return_201_when_add_a_parking_order() throws  Exception {
        //Given
        Car car = new Car("C001");
        ParkingOrder parkingOrder = new ParkingOrder("停车场1",car.getCarNum());
        String objectJson = new JSONObject(parkingOrder).toString();
        //when+then
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
//        Assertions.assertEquals("d",parkingOrderNew.getStartTime());
        String objectJson = new JSONObject(parkingOrderNew).toString();

        this.mockMvc.perform(put("/parkingOrders/"+parkingOrderNew.getOrderId()).contentType(MediaType.APPLICATION_JSON_UTF8).
                content(objectJson)).andExpect(status().isOk());
        Assertions.assertEquals("OFF",parkingOrderRepository.findById(id).get().getStatus());
        //when+then

    }

}
