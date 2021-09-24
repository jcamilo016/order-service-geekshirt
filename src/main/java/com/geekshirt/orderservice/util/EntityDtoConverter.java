package com.geekshirt.orderservice.util;

import com.geekshirt.orderservice.dto.OrderResponse;
import com.geekshirt.orderservice.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {

    @Autowired
    ModelMapper modelMapper;

    public OrderResponse convertEntityToDto(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }
}
