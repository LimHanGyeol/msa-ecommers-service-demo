package com.tommy.orders.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tommy.orders.domain.Orders;
import com.tommy.orders.dto.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class OrdersResponse {
    private String productCode;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private LocalDateTime createdAt;

    private String ordersCode;

    private OrdersResponse(OrderDto orderDto) {
        this.productCode = orderDto.getProductId();
        this.quantity = orderDto.getQuantity();
        this.unitPrice = orderDto.getUnitPrice();
        this.totalPrice = orderDto.getTotalPrice();
        this.ordersCode = orderDto.getOrderCode();
    }

    public OrdersResponse(Orders orders) {
        this.productCode = orders.getProductId();
        this.quantity = orders.getQuantity();
        this.unitPrice = orders.getUnitPrice();
        this.totalPrice = orders.getTotalPrice();
        this.ordersCode = orders.getOrdersCode();
        this.createdAt = orders.getCreatedAt();
    }

    public static OrdersResponse of(OrderDto orderDto) {
        return new OrdersResponse(orderDto);
    }


}
