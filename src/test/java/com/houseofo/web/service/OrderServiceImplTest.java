package com.houseofo.web.service;

import com.houseofo.data.dtos.OrderDto;
import com.houseofo.data.model.*;
import com.houseofo.data.repository.OrderRepository;
import com.houseofo.data.repository.UserRepository;
import com.houseofo.exceptions.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Service
@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;


    @Mock
    UserRepository userRepository;

    @InjectMocks
    OrderServiceImpl orderService = new OrderServiceImpl(new ModelMapper());

    Order order;
    Order order1;
    User user;


    @BeforeEach
    void setUp() {

        order = new Order();
        order1 = new Order();
        user = new User();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findOrderByDateOrdered() {
        LocalDate dateOrdered = LocalDate.now();
        orderService.findOrderByDateOrdered(dateOrdered);
        verify(orderRepository).findOrdersByDateOrdered(dateOrdered);;
    }

    @Test
    void findCompletedOrders() {
        orderService.findCompletedOrders();
        verify(orderRepository).findOrdersByOrderStatus(OrderStatus.COMPLETED);
    }

    @Test
    void createOrder() throws OrderException {
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId("001");
        //when
        when(userRepository.findUserById(anyString())).thenReturn(user);
        orderService.createOrder(orderDto);
        //then
        verify(orderRepository).save(any());
    }

    @Test
    void findOrderById() throws OrderException {
        //given
        //when
        String id = "001";
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));
        orderService.findById(id);
        //then
        verify(orderRepository).findById(id);
    }

    @Test
    void orderCanBeCancelled() throws OrderException {
        //given
        //when
        String id = "001";
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));
        orderService.cancelOrder(id);

        ArgumentCaptor<Order> orderArgumentCaptor
                = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());
        Order changedOrder = orderArgumentCaptor.getValue();
        assertThat(changedOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCELLED);

    }

}