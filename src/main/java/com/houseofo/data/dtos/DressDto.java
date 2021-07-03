package com.houseofo.data.dtos;

import com.houseofo.data.model.Size;
import com.houseofo.data.model.Type;
import com.houseofo.data.model.User;
import lombok.Data;


@Data
public class DressDto {
    private String name;
    private Size size;
    private String image;
    private Type type;
    private User designer;
}
