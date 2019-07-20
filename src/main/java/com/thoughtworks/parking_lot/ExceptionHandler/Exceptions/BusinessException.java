package com.thoughtworks.parking_lot.ExceptionHandler.Exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
