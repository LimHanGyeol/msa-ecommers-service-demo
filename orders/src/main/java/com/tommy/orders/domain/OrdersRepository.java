package com.tommy.orders.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrdersCode(String orderCode);
    List<Orders> findByAccountCode(String accountCode);
}
