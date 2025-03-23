package com.immfly.payments.domain.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private final Long id;
    private final String cardToken;
    private final PaymentGatewayType paymentGateway;
    private final PaymentStatus status;
    private final LocalDateTime timestamp;

    public Payment(Long id,
                   String cardToken,
                   PaymentGatewayType paymentGateway,
                   PaymentStatus status,
                   LocalDateTime timestamp) {
        Objects.requireNonNull(id, "id must not be null");
        this.id = id;
        this.cardToken = cardToken;
        this.paymentGateway = paymentGateway;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Long id() { return id;}
    public String cardToken() { return cardToken; }
    public PaymentGatewayType paymentGateway() { return paymentGateway; }
    public PaymentStatus status() { return status; }
    public LocalDateTime timestamp() { return timestamp; }
}
