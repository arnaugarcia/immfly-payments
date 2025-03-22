package com.immfly.payments.infrastructure.entity;


import com.immfly.payments.domain.model.OrderStatus;

public enum OrderStatusEntity {
    OPEN, DROPPED, FINISHED, CANCELED;

    public static OrderStatusEntity fromDomain(OrderStatus status) {
        return OrderStatusEntity.valueOf(status.name());
    }

    public OrderStatus toDomain() {
        return OrderStatus.valueOf(this.name());
    }
}
