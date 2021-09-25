package com.tommy.accounts.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountOrdersResponse {
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private LocalDateTime createdAt;
    private String orderId;
}
