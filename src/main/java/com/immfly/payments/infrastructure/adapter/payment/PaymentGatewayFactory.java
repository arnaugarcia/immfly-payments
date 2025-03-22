package com.immfly.payments.infrastructure.adapter.payment;

import com.immfly.payments.domain.model.PaymentGatewayType;
import jakarta.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayFactory {

    private final StripePaymentGateway stripePaymentGateway;
    private final AydenPaymentGateway aydenPaymentGateway;
    private final MockPaymentGateway mockPaymentGateway;

    private final Map<PaymentGatewayType, PaymentGateway> gateways = new EnumMap<>(PaymentGatewayType.class);

    @PostConstruct
    public void init() {
        gateways.put(PaymentGatewayType.STRIPE, stripePaymentGateway);
        gateways.put(PaymentGatewayType.AYDEN, aydenPaymentGateway);
        gateways.put(PaymentGatewayType.MOCK, mockPaymentGateway);
    }

    public PaymentGateway getGateway(String gateway) {
        var type = PaymentGatewayType.fromString(gateway);
        if (!gateways.containsKey(type)) {
            throw new IllegalArgumentException("Unsupported payment gateway: " + type);
        }
        return gateways.get(type);
    }
}
