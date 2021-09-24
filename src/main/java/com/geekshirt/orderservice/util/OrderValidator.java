package com.geekshirt.orderservice.util;

import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.exception.IncorrectOrderRequestException;
import org.apache.commons.lang3.StringUtils;

public class OrderValidator {
    public static void validateOrder(OrderRequest orderRequest) {
        if (StringUtils.isBlank(orderRequest.getAccountId())) {
            throw new IncorrectOrderRequestException(ExceptionMessagesEnum.INCORRECT_REQUEST.getValue());
        }
    }
}
