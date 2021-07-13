package com.houseofo.data.repository;

import com.houseofo.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
@Slf4j
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    Order order1;
    Order order2;
    Order order3;
    Address address;
    Address address2;


    @BeforeEach
    void setUp() {
        order1 = new Order();
        order2 = new Order();
        order3 = new Order();
        address = new Address();
        address2 = new Address();
    }
    @AfterEach
    void tearDown(){
        orderRepository.deleteAll();
    }



    @Test
    void findOrdersByDateOrdered() {
        LocalDate orderDate = LocalDate.now();

        address.setHouseNumber(12);
        address.setStreetName("maryland street");
        address.setState("Lagos");
        address.setCountry("Nigeria");

        order1.setAddress(address);
        order1.setDateOrdered(orderDate);


        orderRepository.save(order1);

        LocalDate orderDate2 = LocalDate.of(2021, 1,18);
        address2.setHouseNumber(312);
        address2.setStreetName("herbert street");
        address2.setState("Lagos");
        address2.setCountry("Nigeria");

        order2.setAddress(address2);
        order2.setDateOrdered(orderDate2);

        orderRepository.save(order2);
        log.info("order1 after saving in db-->{}",order1);
        log.info("order2 after saving in db-->{}",order2);


        List<Order> orders = orderRepository.findOrdersByDateOrdered(orderDate);
        assertThat(orders.get(0).getDateOrdered()).isEqualTo(orderDate);
        assertThat(orders.size()).isEqualTo(1);
    }

    @Test
    void findByOrderStatusCompleted() {
        order1.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order1);

        List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.COMPLETED);
        assertThat(orderList.size()).isEqualTo(1);
        assertThat(orderList.get(0).getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void findByOrderStatusInProgress(){
        order2.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.IN_PROGRESS);
        assertThat(orders.size()).isEqualTo(1);
        assertThat(orders.get(0).getOrderStatus()).isEqualTo(OrderStatus.IN_PROGRESS);
    }

    @Test
    void findByOrderStatusCancelled(){
        order3.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order3);
        log.info("after saving order ->{}", order3);

        List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.CANCELLED);
        assertThat(orders.size()).isEqualTo(1);
        assertThat(orders.get(0).getOrderStatus()).isEqualTo(OrderStatus.CANCELLED);
    }

}