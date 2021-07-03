package com.houseofo.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document
public class Dress {
    @Id
    private String id;
    private Size size;
    private String image;
    private Type type;
    @DBRef
    private User designer;

}
