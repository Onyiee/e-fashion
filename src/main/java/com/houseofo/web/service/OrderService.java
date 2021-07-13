package com.houseofo.web.service;

import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.Order;
import com.houseofo.data.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface OrderService {
    List<OrderDto> findOrderByDateOrdered(LocalDate dateOrdered);
    List<OrderDto> findCompletedOrders();
    OrderDto createOrder(OrderDto orderDto);
    void cancelOrder(String id);
}
