package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentStatus;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway implements PaymentGateway {
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Payment processPayment(String cardToken, String gatewayName, java.math.BigDecimal amount, PaymentStatus status) {
        // Simulate payment processing delay or logic here.
        return new Payment(idCounter.incrementAndGet(), cardToken, gatewayName, status, LocalDateTime.now());
    }
}
