package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGatewayFactory;
import com.immfly.payments.infrastructure.dto.OrderDTO;

public class FinishOrderUseCase {
    private final OrderRepository orderRepository;
    private final PaymentGatewayFactory paymentGatewayFactory;

    public FinishOrderUseCase(OrderRepository orderRepository, PaymentGatewayFactory paymentGatewayFactory) {
        this.orderRepository = orderRepository;
        this.paymentGatewayFactory = paymentGatewayFactory;
    }

    public OrderDTO finishOrder(Long orderId, String cardToken, String gatewayName) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var gateway = paymentGatewayFactory.getGateway(gatewayName);
        var payment = gateway.processPayment(cardToken, order.totalPrice());
        order.finishOrder(payment);
        var result = orderRepository.save(order);
        return OrderDTO.fromDomain(result);
    }
}
