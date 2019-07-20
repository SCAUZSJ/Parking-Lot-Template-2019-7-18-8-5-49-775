package com.thoughtworks.parking_lot.Enum;

public enum  ParamErrorMsg {

    PARAM_ILLEGAL("参数不合法");

    private String message;

    ParamErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
