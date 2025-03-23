package com.immfly.payments.application.usecase;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentStatus;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.PaymentRepository;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGateway;
import com.immfly.payments.infrastructure.adapter.payment.PaymentGatewayFactory;
import com.immfly.payments.infrastructure.dto.OrderDTO;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FinishOrderUseCase")
class FinishOrderUseCaseTest {

    private FinishOrderUseCase useCase;
    private OrderRepository orderRepo;
    private PaymentRepository paymentRepo;
    private PaymentGatewayFactory gatewayFactory;

    @BeforeEach
    void setUp() {
        orderRepo = mock(OrderRepository.class);
        paymentRepo = mock(PaymentRepository.class);
        gatewayFactory = mock(PaymentGatewayFactory.class);
        useCase = new FinishOrderUseCase(orderRepo, gatewayFactory, paymentRepo);
    }

    @Test
    void shouldFinishOrderWhenValid() {
        Long id = new Random().nextLong();
        Order order = mock(Order.class);
        PaymentGateway gateway = mock(PaymentGateway.class);
        Payment payment = mock(Payment.class);

        when(orderRepo.findById(id)).thenReturn(Optional.of(order));
        when(gatewayFactory.getGateway("MOCK")).thenReturn(gateway);
        when(gateway.processPayment(any(), any())).thenReturn(payment);
        when(orderRepo.save(any())).thenReturn(order);
        when(payment.status()).thenReturn(PaymentStatus.PAID);

         useCase.finishOrder(id, "token", "MOCK");

        verify(order).finishOrder(any());
        verify(orderRepo).save(order);
    }

    @Test
    void shouldThrowWhenOrderNotFound() {
       Long id = new Random().nextLong();
        when(orderRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> useCase.finishOrder(id, "x", "MOCK"));
    }
}
