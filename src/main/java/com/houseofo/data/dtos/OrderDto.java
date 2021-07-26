package com.houseofo.data.dtos;

import com.houseofo.data.model.Address;
import com.houseofo.data.model.Item;
import com.houseofo.data.model.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
@Data
public class OrderDto {
    private String id;
    @NotNull(message = "Item field  must be entered.")
    private Map<String, Item> items;
    private Address address;
    private OrderStatus orderStatus;
    @NotBlank(message = "User Id must be present")
    private String userId;
}
