package com.houseofo.data.dtos;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DressOrderRequest {
    private String userId;
    private Map<String, Integer> dressOrderDetails =new HashMap<>();
}
