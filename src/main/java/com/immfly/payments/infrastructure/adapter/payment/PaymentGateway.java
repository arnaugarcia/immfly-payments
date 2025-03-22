package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentStatus;
import java.math.BigDecimal;

public interface PaymentGateway {
    Payment processPayment(String cardToken, BigDecimal amount);
}
