package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceClient {

    private final RestTemplate restTemplate;

    @Autowired
    OrderServiceConfig orderServiceConfig;

    public CustomerServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public AccountDto findAccountById(String accountId) {
        return restTemplate.getForObject( orderServiceConfig.getCustomerServiceUrl() + "/{id}", AccountDto.class, accountId);
    }

    public AccountDto createAccount(AccountDto account) {
        return restTemplate.postForObject(orderServiceConfig.getCustomerServiceUrl(), account, AccountDto.class);
    }

    public void updateAccount(AccountDto account) {
        restTemplate.put(orderServiceConfig.getCustomerServiceUrl() + "/{id}", account, account.getId());
    }

    public void deleteAccount(AccountDto account) {
        restTemplate.delete(orderServiceConfig.getCustomerServiceUrl() + "/{id}", account.getId());
    }
}
