package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGateway;

public class FinishOrderUseCase {
    private final OrderRepository orderRepository;
    private final PaymentGateway paymentGateway;

    public FinishOrderUseCase(OrderRepository orderRepository, PaymentGateway paymentGateway) {
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
    }

    public Order finishOrder(Long orderId, String cardToken, String gatewayName, Payment.Status paymentStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        // Process payment with the correct provider (could be polymorphic)
        Payment payment = paymentGateway.processPayment(cardToken, gatewayName, order.totalPrice(), paymentStatus);
        order.finishOrder(payment);
        return orderRepository.save(order);
    }
}
