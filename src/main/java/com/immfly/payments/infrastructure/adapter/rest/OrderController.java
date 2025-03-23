package com.immfly.payments.infrastructure.adapter.rest;

import com.immfly.payments.application.usecase.CancelOrderUseCase;
import com.immfly.payments.application.usecase.CreateOrderUseCase;
import com.immfly.payments.application.usecase.FinishOrderUseCase;
import com.immfly.payments.application.usecase.UpdateOrderUseCase;
import com.immfly.payments.infrastructure.dto.OrderDTO;
import com.immfly.payments.infrastructure.dto.OrderRequestDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final CancelOrderUseCase cancelOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final FinishOrderUseCase finishOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestParam String seatLetter, @RequestParam Integer seatNumber) {
        OrderDTO order = createOrderUseCase.createOrder(seatLetter, seatNumber);
        return ResponseEntity.ok(order);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Order canceled successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        cancelOrderUseCase.cancel(orderId);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product added successfully"),
        @ApiResponse(responseCode = "404", description = "Order or product not found")
    })
    @PutMapping("/{orderId}/products/{productId}")
    public ResponseEntity<OrderDTO> addProduct(@PathVariable Long orderId, @PathVariable Long productId) {
        var order = updateOrderUseCase.addProductToOrder(orderId, productId);
        return ResponseEntity.ok(order);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDTO orderDto) {
        OrderDTO order = updateOrderUseCase.updateOrder(orderId, orderDto);
        return ResponseEntity.ok(order);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order finished successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PostMapping("/{orderId}")
    public ResponseEntity<OrderDTO> finishOrder(@PathVariable Long orderId,
                                             @RequestParam String cardToken,
                                             @RequestParam String gateway) {
        OrderDTO order = finishOrderUseCase.finishOrder(orderId, cardToken, gateway);
        return ResponseEntity.ok(order);
    }
}

