package com.houseofo.data.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
public class DressOrderRequest {
    @NotBlank(message = "User Id must be present")
    private String userId;
    @NotNull(message = "Field must be filled.")
    private Map<String, Integer> dressOrderDetails =new HashMap<>();
}
