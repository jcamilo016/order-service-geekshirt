package com.geekshirt.orderservice.dto;

import com.geekshirt.orderservice.util.AccountStatus;
import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private AddressDto address;
    private CustomerDto customer;
    private CreditCardDto creditCard;
    private AccountStatus status;
}
