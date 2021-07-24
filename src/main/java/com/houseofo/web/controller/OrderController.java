package com.houseofo.web.controller;

import com.houseofo.data.dtos.ApiResponse;
import com.houseofo.data.dtos.OrderDto;
import com.houseofo.exceptions.OrderException;
import com.houseofo.web.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("byId/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        try {
            OrderDto dto = orderService.findById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (OrderException orderException){
            ApiResponse response = new ApiResponse(false, orderException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{date}")
    public ResponseEntity<?> getOrderByDateOrdered(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
            List<OrderDto> orderDtos = orderService.findOrderByDateOrdered(date);
            return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getCompletedOrders(){
        List<OrderDto> orderDtos = orderService.findCompletedOrders();
        log.info("the orders returned are -->{}", orderDtos);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto dto){
        try {
            OrderDto orderDto = orderService.createOrder(dto);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        }catch (OrderException exception){
            ApiResponse response = new ApiResponse(false, "Order already exists. Create another.");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable String id){
        try {
            orderService.cancelOrder(id);
            ApiResponse apiResponse = new ApiResponse(true, "Order cancelled successfully.");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (OrderException exception){
            ApiResponse apiResponse = new ApiResponse(false, "Order cannot be cancelled.");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}

