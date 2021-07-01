package com.houseofo.data.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
public class Address {
    private int houseNumber;
    private String streetName;
    private String LGA;
    private String state;
    private String country;
    private String landmark;
}
