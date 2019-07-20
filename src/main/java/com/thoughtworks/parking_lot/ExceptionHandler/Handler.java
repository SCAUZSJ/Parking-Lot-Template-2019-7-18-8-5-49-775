package com.thoughtworks.parking_lot.ExceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Handler {
    /**
     *
     * @param exception
     * @return 统一返回携带errorCode和errorMsg的对象
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity exceptionHandler(RuntimeException exception){
       return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}