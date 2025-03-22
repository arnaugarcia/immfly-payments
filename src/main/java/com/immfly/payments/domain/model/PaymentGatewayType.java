package com.immfly.payments.domain.model;

public enum PaymentGatewayType {
    STRIPE,
    AYDEN,
    MOCK;

    public static PaymentGatewayType fromString(String name) {
        return PaymentGatewayType.valueOf(name.toUpperCase());
    }
}
