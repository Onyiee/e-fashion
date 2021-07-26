package com.houseofo.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;
@Data
@Document

public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private Role role;
    private List<Dress> dresses = new ArrayList<>();
    private Size size;
    @DBRef
    private List<Order> orders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private String designerBrand;
}
