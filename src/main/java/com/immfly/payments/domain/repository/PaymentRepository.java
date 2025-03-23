package com.immfly.payments.domain.repository;

import com.immfly.payments.domain.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
