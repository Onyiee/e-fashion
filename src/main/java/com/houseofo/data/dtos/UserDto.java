package com.houseofo.data.dtos;

import com.houseofo.data.model.*;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
public class UserDto {
    private String id;
    @NotBlank(message = "You must enter a first name.")
    @javax.validation.constraints.Size(min = 2, message = "First name must be at least two characters.",max = 20)
    private String firstName;
    @NotBlank(message = "You must enter a last name.")
    @javax.validation.constraints.Size(min = 2, message = "Last name must be at least two characters.", max = 20)
    private String lastName;
    @NotBlank(message = "You must enter a user name.")
    @javax.validation.constraints.Size(min = 2, message = "User name must be at least two characters.", max = 20)
    private String userName;
    private Role role;
    private List<Dress> dresses;
    private Size size;
    private List<Order> orders;
    private List<Address> addresses;
    private String designerBrand;
    private String password;
}
