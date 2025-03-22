package com.immfly.payments.infrastructure.adapter.rest;

import com.immfly.payments.application.usecase.CreateOrderUseCase;
import com.immfly.payments.application.usecase.FinishOrderUseCase;
import com.immfly.payments.application.usecase.UpdateOrderUseCase;
import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final FinishOrderUseCase finishOrderUseCase;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam String seatLetter, @RequestParam Integer seatNumber) {
        Order order = createOrderUseCase.createOrder(seatLetter, seatNumber);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Order> addProduct(@PathVariable Long orderId, @PathVariable Long productId) {
        Order order = updateOrderUseCase.addProductToOrder(orderId, productId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/finish")
    public ResponseEntity<Order> finishOrder(@PathVariable Long orderId,
                                             @RequestParam String cardToken,
                                             @RequestParam String gatewayName,
                                             @RequestParam PaymentStatus paymentStatus) {
        Order order = finishOrderUseCase.finishOrder(orderId, cardToken, gatewayName, paymentStatus);
        return ResponseEntity.ok(order);
    }
}

