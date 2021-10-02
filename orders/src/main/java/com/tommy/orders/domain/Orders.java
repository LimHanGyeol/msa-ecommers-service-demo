package com.tommy.orders.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "ec_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false, unique = true)
    private String accountCode;

    @Column(nullable = false)
    private String ordersCode;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private Orders(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, String accountCode, String ordersCode) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.accountCode = accountCode;
        this.ordersCode = ordersCode;
        this.createdAt = LocalDateTime.now();
    }

    public static Orders of(String productId, int quantity, int unitPrice, int totalPrice, String accountCode, String ordersCode) {
        return new Orders(productId, quantity, unitPrice, totalPrice, accountCode, ordersCode);
    }
}
