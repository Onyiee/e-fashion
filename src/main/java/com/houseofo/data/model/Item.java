package com.houseofo.data.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private Dress dress;
    private int quantity;
    private BigDecimal totalPrice;
}
