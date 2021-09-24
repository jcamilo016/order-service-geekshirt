package com.geekshirt.orderservice.dto;

import com.geekshirt.orderservice.util.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    private OrderStatus status;
    private String accountId;
    private Double totalAmount;
    private Double totalTax;
    private Date transactionDate;
}
