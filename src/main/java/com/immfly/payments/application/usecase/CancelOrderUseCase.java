package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.repository.OrderRepository;

public class CancelOrderUseCase {

    private final OrderRepository orderRepository;

    public CancelOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void cancel(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();
        order.cancel();
        orderRepository.save(order);
    }
}
