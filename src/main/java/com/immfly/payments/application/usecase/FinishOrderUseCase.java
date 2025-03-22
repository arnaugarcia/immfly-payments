package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGateway;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGatewayFactory;

public class FinishOrderUseCase {
    private final OrderRepository orderRepository;
    private final PaymentGatewayFactory paymentGatewayFactory;

    public FinishOrderUseCase(OrderRepository orderRepository, PaymentGatewayFactory paymentGatewayFactory) {
        this.orderRepository = orderRepository;
        this.paymentGatewayFactory = paymentGatewayFactory;
    }

    public Order finishOrder(Long orderId, String cardToken, String gatewayName) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        PaymentGateway gateway = paymentGatewayFactory.getGateway(gatewayName);
        Payment payment = gateway.processPayment(cardToken, order.totalPrice());
        order.finishOrder(payment);
        return orderRepository.save(order);
    }
}
