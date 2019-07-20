package com.thoughtworks.parking_lot.Enum;

public enum BusinessErrorMsg {

    PARKING_LOT_FULL("停车场已经满了");


    private String message;

    BusinessErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
