package com.geekshirt.orderservice.service;

import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.entities.Order;
import com.geekshirt.orderservice.exception.IncorrectOrderRequestException;
import com.geekshirt.orderservice.repositories.OrderRepository;
import com.geekshirt.orderservice.util.ExceptionMessagesEnum;
import com.geekshirt.orderservice.util.OrderStatus;
import com.geekshirt.orderservice.util.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest) {
        OrderValidator.validateOrder(orderRequest);

        Order order = new Order();
        order.setAccountId(orderRequest.getAccountId());
        order.setOrderId("9999");
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(100.00);
        order.setTotalTax(10.00);
        order.setTransactionDate(new Date());
        return order;
    }

    public List<Order> findAllOrders() {
/*        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setAccountId("999819");
        order1.setOrderId("12345");
        order1.setStatus(OrderStatus.PENDING);
        order1.setTotalAmount(100.00);
        order1.setTotalTax(10.00);
        order1.setTransactionDate(new Date());

        Order order2 = new Order();
        order2.setAccountId("999819");
        order2.setOrderId("12346");
        order2.setStatus(OrderStatus.PENDING);
        order2.setTotalAmount(120.00);
        order2.setTotalTax(10.00);
        order2.setTransactionDate(new Date());

        orderList.add(order1);
        orderList.add(order2);*/

        return Optional
                .of(orderRepository.findAll())
                .orElseThrow(() -> new IncorrectOrderRequestException(ExceptionMessagesEnum.INCORRECT_REQUEST.getValue()));
    }

    public Order findOrder(String orderId) {
        return orderRepository
                .findByOrderId(orderId)
                .orElseThrow(() -> new IncorrectOrderRequestException(ExceptionMessagesEnum.INCORRECT_REQUEST.getValue()));
    }

}
