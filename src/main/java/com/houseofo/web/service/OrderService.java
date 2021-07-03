package com.houseofo.web.service;

import com.houseofo.data.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByDateOrdered(); //what wil this accept
    List<Order> findCompletedOrders();
}
