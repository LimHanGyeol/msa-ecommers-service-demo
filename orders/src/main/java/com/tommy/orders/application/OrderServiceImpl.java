package com.tommy.orders.application;

import com.tommy.orders.domain.Orders;
import com.tommy.orders.domain.OrdersRepository;
import com.tommy.orders.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        String ordersCode = UUID.randomUUID().toString();
        orderDto.setOrdersCode(ordersCode);
        orderDto.updateTotalPrice();

        Orders orders = createOrders(orderDto);

        ordersRepository.save(orders);

        return new OrderDto(orders);
    }

    @Override
    public OrderDto getOrderByOrderCode(String orderCode) {
        Orders orders = ordersRepository.findByOrdersCode(orderCode)
                .orElseThrow(() -> new RuntimeException("not found orders"));
        return new OrderDto(orders);
    }

    @Override
    public List<Orders> getOrdersByAccountCode(String accountCode) {
        return ordersRepository.findByAccountCode(accountCode);
    }

    private Orders createOrders(OrderDto orderDto) {
        return Orders.of(orderDto.getProductId(), orderDto.getQuantity(), orderDto.getUnitPrice(), orderDto.getTotalPrice(), orderDto.getAccountCode(), orderDto.getOrderCode());
    }
}
