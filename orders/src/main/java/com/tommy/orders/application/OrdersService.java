package com.tommy.orders.application;

import com.tommy.orders.domain.Orders;
import com.tommy.orders.dto.OrderDto;

import java.util.List;

public interface OrdersService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderCode(String orderCode);
    List<Orders> getOrdersByAccountCode(String accountCode);
}
