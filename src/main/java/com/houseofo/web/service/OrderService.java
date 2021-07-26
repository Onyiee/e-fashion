package com.houseofo.web.service;

import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.Order;
import com.houseofo.exceptions.OrderException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface OrderService {
    OrderDto findById(String id) throws OrderException;
    List<OrderDto> findOrderByDateOrdered(LocalDate dateOrdered);
    List<OrderDto> findCompletedOrders();
    OrderDto createOrder(OrderDto orderDto) throws OrderException;
    void cancelOrder(String id) throws OrderException;
    Order saveOrder(Order order);
}
