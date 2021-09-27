package com.tommy.catalogs.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tommy.catalogs.domain.Catalog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogResponse {
    private final String productId;
    private final String productName;
    private final int unitPrice;
    private final int stock;
    private final LocalDateTime createdAt;

    public CatalogResponse(Catalog catalog) {
        this.productId = catalog.getProductId();
        this.productName = catalog.getProductName();
        this.unitPrice = catalog.getUnitPrice();
        this.stock = catalog.getStock();
        this.createdAt = catalog.getCreatedAt();
    }
}
