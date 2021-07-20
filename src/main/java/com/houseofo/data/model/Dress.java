package com.houseofo.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Dress {
    @Id
    private String id;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
    private Size size;
    private String image;
    private Type type;
    @DBRef
    private User designer;

}
