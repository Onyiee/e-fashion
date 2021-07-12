package com.houseofo.web.service;

import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<OrderDto> findOrderByDateOrdered(LocalDate dateOrdered); //what wil this accept
    List<OrderDto> findCompletedOrders();
}
