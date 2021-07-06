package com.houseofo.data.dtos;

import com.houseofo.data.model.Address;
import com.houseofo.data.model.Item;

import java.time.LocalDate;
import java.util.Map;

public class OrderDto {
    private String id;
    private LocalDate dateOrdered;
    private Map<String, Item> items;
    private Address address;
    private boolean completed;
}
