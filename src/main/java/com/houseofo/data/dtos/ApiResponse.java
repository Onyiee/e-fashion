package com.houseofo.data.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private boolean isSuccessful;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();

    public ApiResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
