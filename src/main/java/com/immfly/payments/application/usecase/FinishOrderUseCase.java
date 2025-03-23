package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.PaymentRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGatewayFactory;
import com.immfly.payments.infrastructure.dto.OrderDTO;

public class FinishOrderUseCase {
    private final OrderRepository orderRepository;
    private final PaymentGatewayFactory paymentGatewayFactory;
    private final PaymentRepository paymentRepository;

    public FinishOrderUseCase(OrderRepository orderRepository, PaymentGatewayFactory paymentGatewayFactory,
                              PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentGatewayFactory = paymentGatewayFactory;
        this.paymentRepository = paymentRepository;
    }

    public OrderDTO finishOrder(Long orderId, String cardToken, String gatewayName) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var gateway = paymentGatewayFactory.getGateway(gatewayName);
        var payment = gateway.processPayment(cardToken, order.totalPrice());
        Payment resultPayment = paymentRepository.save(payment);
        order.finishOrder(resultPayment);
        var result = orderRepository.save(order);
        return OrderDTO.fromDomain(result);
    }
}
