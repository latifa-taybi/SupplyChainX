package com.example.supplyChainXProject.exception;

import com.example.supplyChainXProject.apiResponse.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> handlerRunTimeExcption(RuntimeException e){
        MessageResponse messageResponse = new MessageResponse(e.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
    }
}