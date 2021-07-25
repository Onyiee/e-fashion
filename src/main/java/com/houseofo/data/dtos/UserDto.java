package com.houseofo.data.dtos;

import com.houseofo.data.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private Role role;
    private List<Dress> dresses;
    private Size size;
    private List<Order> orders;
    private List<Address> addresses;
    private String designerBrand;

}
