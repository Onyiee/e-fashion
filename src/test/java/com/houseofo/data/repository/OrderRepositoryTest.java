package com.houseofo.data.repository;

import com.houseofo.data.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    Order order1;
    Order order2;
    Address address;
    Address address2;


    @BeforeEach
    void setUp() {
        order1 = new Order();
        order2 = new Order();
        address = new Address();
        address2 = new Address();

    }

    @Test
    void findOrdersByDateOrdered() {
        LocalDate orderDate = LocalDate.now();

        address.setHouseNumber(12);
        address.setStreetName("maryland street");
        address.setState("Lagos");
        address.setCountry("Nigeria");

        order1.setCompleted(true);
        order1.setAddress(address);
        order1.setDateOrdered(orderDate);


        orderRepository.save(order1);

        LocalDate orderDate2 = LocalDate.now();
        address2.setHouseNumber(312);
        address2.setStreetName("herbert street");
        address2.setState("Lagos");
        address2.setCountry("Nigeria");

        order2.setCompleted(false);
        order2.setAddress(address2);
        order2.setDateOrdered(orderDate2);

        orderRepository.save(order2);
        System.out.println(order1);
        System.out.println(order2);

        List<Order> orders = orderRepository.findOrdersByDateOrdered(orderDate);
        assertThat(orders.get(0).getDateOrdered()).isEqualTo(orderDate);
        assertThat(orders.get(1).getDateOrdered()).isEqualTo(orderDate2);
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    void findOrdersByCompleted() {
    }
}