package com.tommy.orders.vo;

import lombok.Getter;

@Getter
public class OrdersRequest {
    private String productId;
    private int quantity;
    private int unitPrice;
}
