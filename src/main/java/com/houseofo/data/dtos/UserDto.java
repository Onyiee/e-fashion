package com.houseofo.data.dtos;

import com.houseofo.data.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private Role role;
    private List<Dress> dresses;
    private Size size;
    private List<Order> orders;
    private List<Address> addresses;
    private String designerBrand;

}
