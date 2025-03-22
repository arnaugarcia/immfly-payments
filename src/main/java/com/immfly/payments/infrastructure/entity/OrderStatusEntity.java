package com.immfly.payments.infrastructure.entity;


import com.immfly.payments.domain.model.OrderStatus;


public enum OrderStatusEntity {
    OPEN, DROPPED, FINISHED, CANCELED;

    public OrderStatus toDomain() {
        // Since the names are identical, simply use valueOf
        return OrderStatus.valueOf(this.name());
    }

    public static OrderStatusEntity fromDomain(OrderStatus status) {
        return OrderStatusEntity.valueOf(status.name());
    }
}
