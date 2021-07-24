package com.houseofo.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
@Data
@Document

public class Order {
    @Id
    private String id;
    private LocalDate dateOrdered;
    private Map<String, Item> items = new HashMap<>();
    private Address address;
    private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

}
