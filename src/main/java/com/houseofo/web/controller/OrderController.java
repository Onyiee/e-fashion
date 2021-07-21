package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.OrderDto;
import com.houseofo.web.service.OrderException;
import com.houseofo.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        try {
            OrderDto dto = orderService.findById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (OrderException orderException){
            ApiResponse response = new ApiResponse(false, orderException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
