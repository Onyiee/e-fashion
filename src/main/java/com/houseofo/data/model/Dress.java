package com.houseofo.data.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
@Data

@Document
public class Dress {
    @Id
    private String id;
    private String name;
    private Size size;
    private String image;
    private Type type;
    @DBRef
    private User designer;

}
