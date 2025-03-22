package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.Payment;
import java.math.BigDecimal;

public interface PaymentGateway {
    Payment processPayment(String cardToken, String gatewayName, BigDecimal amount, Payment.Status status);
}
