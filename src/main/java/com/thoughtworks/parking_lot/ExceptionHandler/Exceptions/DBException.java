package com.thoughtworks.parking_lot.ExceptionHandler.Exceptions;

public class DBException extends RuntimeException {

    public DBException(String message) {
        super(message);
    }
}
