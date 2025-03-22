package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentGatewayType;
import com.immfly.payments.domain.model.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Payment processPayment(String cardToken, BigDecimal amount) {

        return new Payment(idCounter.incrementAndGet(), cardToken, PaymentGatewayType.STRIPE, PaymentStatus.PAID,
            LocalDateTime.now());
    }
}
