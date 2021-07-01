package com.houseofo.data.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

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
