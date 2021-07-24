package com.houseofo.data.repository;

import com.houseofo.data.model.Order;
import com.houseofo.data.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findOrdersByDateOrdered(LocalDate dateTime);
    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);
}