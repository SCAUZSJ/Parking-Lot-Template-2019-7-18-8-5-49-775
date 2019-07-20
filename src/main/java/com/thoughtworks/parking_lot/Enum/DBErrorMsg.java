package com.thoughtworks.parking_lot.Enum;

public enum DBErrorMsg {

    DB_INSERT_ERROR("数据库插入操作异常"),
    DB_UPDATE_ERROR("数据库更新操作异常"),
    DB_DELETE_ERROR("数据库插入操作异常");

    private String message;

    DBErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
