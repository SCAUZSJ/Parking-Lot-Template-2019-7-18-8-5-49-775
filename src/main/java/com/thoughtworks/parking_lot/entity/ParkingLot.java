package com.thoughtworks.parking_lot.entity;

import javax.persistence.*;

@Entity
@Table
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    private int capacity;

    private String location;

    public  ParkingLot(){}

    public ParkingLot(int capacity, String location) {
        this.capacity = capacity;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
