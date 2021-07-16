package com.houseofo.web.service;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.Dress;
import com.houseofo.data.model.Order;
import com.houseofo.data.model.OrderStatus;
import com.houseofo.data.repository.DressRepository;
import com.houseofo.data.repository.OrderRepository;
import com.houseofo.exceptions.DressException;
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
    public OrderDto findById(String id) throws OrderException {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderException("Id does not match any order"));
        OrderDto dto = modelMapper.map(order, OrderDto.class);
        return dto;
    }

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
        List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.COMPLETED);
        List<OrderDto> orderDtoList = orderList
                .stream()
                .map(order -> modelMapper.map(order,OrderDto.class))
                .collect(Collectors.toList());
        return orderDtoList;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        orderRepository.save(order);
        return orderDto;
    }

    @Override
    public void cancelOrder(String id) throws OrderException {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderException("Id does not match any order"));
        if (order.getOrderStatus().equals(OrderStatus.COMPLETED)){
            throw new OrderException("Order cannot be cancelled once completed");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
