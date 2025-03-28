package com.immfly.payments.infrastructure.mapper;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentGatewayType;
import com.immfly.payments.infrastructure.entity.PaymentEntity;
import com.immfly.payments.infrastructure.entity.PaymentStatusEntity;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentEntity toEntity(Payment payment) {
        if (payment == null) return null;
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.id());
        entity.setCardToken(payment.cardToken());
        entity.setGateway(payment.paymentGateway().toString());
        entity.setStatus(PaymentStatusEntity.fromDomain(payment.status()));
        entity.setTimestamp(payment.timestamp());
        return entity;
    }

    public static Payment toDomain(PaymentEntity entity) {
        if (entity == null) return null;
        return new Payment(
            entity.getId(),
            entity.getCardToken(),
            PaymentGatewayType.fromString(entity.getGateway()),
            entity.getStatus().toDomain(),
            entity.getTimestamp()
        );
    }
}
