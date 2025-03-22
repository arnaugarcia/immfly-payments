package com.immfly.payments.infrastructure.entity;

import com.immfly.payments.domain.model.PaymentStatus;

public enum PaymentStatusEntity {
    PAID, PAYMENT_FAILED, OFFLINE_PAYMENT;

    public PaymentStatus toDomain() {
        return PaymentStatus.valueOf(this.name());
    }

    public static PaymentStatusEntity fromDomain(PaymentStatus status) {
        return PaymentStatusEntity.valueOf(status.name());
    }
}
