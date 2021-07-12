package com.houseofo.web.service;

import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.Order;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.OrderRepository;
import com.houseofo.util.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DressRepository dressRepository;

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    UserMapper mapper;


    @Override
    public List<OrderDto> findOrderByDateOrdered(LocalDate dateOrdered) {
        List<Order> orders = orderRepository.findOrdersByDateOrdered(dateOrdered);
        List<OrderDto> orderDtos = orders.stream()
                .map(order -> modelMapper.map(order,OrderDto.class))
                .collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> findCompletedOrders() {
        List<Order> orderList = orderRepository.findOrdersByCompletedTrue();
        List<OrderDto> orderDtoList = orderList
                .stream()
                .map(order -> modelMapper.map(order,OrderDto.class))
                .collect(Collectors.toList());
        return orderDtoList;
    }
}
