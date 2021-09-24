package com.geekshirt.orderservice.controller;

import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.dto.OrderResponse;
import com.geekshirt.orderservice.entities.Order;
import com.geekshirt.orderservice.service.OrderService;
import com.geekshirt.orderservice.util.EntityDtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    EntityDtoConverter entityDtoConverter;

    @Operation(summary = "Get the orders list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the orders")})
    @GetMapping(value = "order")
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> orders = orderService
                .findAllOrders()
                .stream()
                .map(order -> entityDtoConverter.convertEntityToDto(order))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Get the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)
    })
    @GetMapping(value = "order/{orderId}")
    public ResponseEntity<OrderResponse> findById(
            @Parameter(description = "id of order to be searched") @PathVariable String orderId
    ) {
        Order order = orderService.findOrder(orderId);
        OrderResponse response = entityDtoConverter.convertEntityToDto(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Order created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content),
    })
    @PostMapping(value="order/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest payload) {
        Order order = orderService.createOrder(payload);
        OrderResponse response = entityDtoConverter.convertEntityToDto(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
