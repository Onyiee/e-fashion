package com.houseofo.data.repository;

import com.houseofo.data.model.Item;
import com.houseofo.data.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findOrdersByDateOrdered(LocalDate dateTime);
    List<Order> findOrdersByCompleted (boolean isCompleted);

}
