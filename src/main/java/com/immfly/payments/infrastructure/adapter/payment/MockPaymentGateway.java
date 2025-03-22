package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.Payment;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway implements PaymentGateway {
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Payment processPayment(String cardToken, String gatewayName, java.math.BigDecimal amount, Payment.Status status) {
        // Simulate payment processing delay or logic here.
        return new Payment(idCounter.incrementAndGet(), cardToken, gatewayName, status, LocalDateTime.now());
    }
}
