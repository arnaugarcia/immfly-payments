package com.immfly.payments.domain.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private final Long id;
    private final String cardToken;
    private final PaymentGatewayType paymentGateway;
    private final PaymentStatus status;
    private final LocalDateTime timestamp;

    public Payment(Long id, String cardToken, PaymentGatewayType paymentGateway, PaymentStatus status, LocalDateTime timestamp) {
        this.id = id;
        this.cardToken = cardToken;
        this.paymentGateway = paymentGateway;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Long id() {
        return id;
    }

    public String cardToken() {
        return cardToken;
    }

    public PaymentGatewayType paymentGateway() {
        return paymentGateway;
    }

    public PaymentStatus status() {
        return status;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
            Objects.equals(cardToken, payment.cardToken) &&
            Objects.equals(paymentGateway, payment.paymentGateway) &&
            Objects.equals(timestamp, payment.timestamp) &&
            status == payment.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(cardToken);
        result = 31 * result + Objects.hashCode(paymentGateway);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }
}
