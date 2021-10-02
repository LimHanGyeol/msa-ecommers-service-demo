package com.tommy.orders.presentation;

import com.tommy.orders.application.OrdersService;
import com.tommy.orders.domain.Orders;
import com.tommy.orders.dto.OrderDto;
import com.tommy.orders.vo.OrdersRequest;
import com.tommy.orders.vo.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/{accountCode}/orders")
    public ResponseEntity<OrdersResponse> createOrders(@PathVariable String accountCode,
                                                       @RequestBody OrdersRequest ordersRequest) {
        OrderDto orderDto = OrderDto.convertedByOrdersRequest(ordersRequest);
        orderDto.setAccountCode(accountCode);

        OrderDto createdOrders = ordersService.createOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(OrdersResponse.of(createdOrders));
    }

    @GetMapping(value = "/{accountCode}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersResponse>> getOrders(@PathVariable String accountCode) {
        List<Orders> orders = ordersService.getOrdersByAccountCode(accountCode);

        List<OrdersResponse> ordersResponses = orders.stream()
                .map(OrdersResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(ordersResponses);
    }
}
