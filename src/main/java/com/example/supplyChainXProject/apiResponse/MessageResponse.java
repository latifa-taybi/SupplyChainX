package com.example.supplyChainXProject.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private String message;
    private LocalDateTime dateTime;

    public MessageResponse(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
