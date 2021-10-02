package com.tommy.orders.dto;

import com.tommy.orders.domain.Orders;
import com.tommy.orders.vo.OrdersRequest;
import lombok.Getter;

@Getter
public class OrderDto {
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private String orderCode;
    private String accountCode;

    public OrderDto(String productId, int quantity, int unitPrice, String orderCode, String accountCode) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = calculateTotalPrice();
        this.orderCode = orderCode;
        this.accountCode = accountCode;
    }

    private OrderDto(String productId, int quantity, int unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderDto(Orders orders) {
        this.productId = orders.getProductId();
        this.quantity = orders.getQuantity();
        this.unitPrice = orders.getUnitPrice();
        this.totalPrice = orders.getTotalPrice();
        this.orderCode = orders.getOrdersCode();
        this.accountCode = orders.getAccountCode();
    }

    public static OrderDto convertedByOrdersRequest(OrdersRequest ordersRequest) {
        return new OrderDto(
                ordersRequest.getProductId(),
                ordersRequest.getQuantity(),
                ordersRequest.getUnitPrice()
        );
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public void setOrdersCode(String ordersCode) {
        this.orderCode = ordersCode;
    }

    public void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

    private int calculateTotalPrice() {
        return quantity * unitPrice;
    }
}
