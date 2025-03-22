package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;

public class CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String seatLetter, Integer seatNumber) {
        Order order = new Order(seatLetter, seatNumber);
        return orderRepository.save(order);
    }
}
