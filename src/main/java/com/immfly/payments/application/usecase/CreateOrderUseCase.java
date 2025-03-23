package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.dto.OrderDTO;

public class CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createOrder(String seatLetter, Integer seatNumber) {
        Order order = Order.create(seatLetter, seatNumber);
        Order result = orderRepository.save(order);
        return OrderDTO.fromDomain(result);
    }
}
