package com.houseofo.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
@Data
@Document

public class User {
    @Id
    private String id;
    private Role role;
    private List<Dress> dresses;
    private Size size;
    @DBRef
    private List<Order> orders;
    private List<Address> addresses;
}
