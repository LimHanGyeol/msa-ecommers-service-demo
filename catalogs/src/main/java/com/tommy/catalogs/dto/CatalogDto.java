package com.tommy.catalogs.dto;

import lombok.Getter;

@Getter
public class CatalogDto {
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;

    private String orderId;
    private String accountId;
}
