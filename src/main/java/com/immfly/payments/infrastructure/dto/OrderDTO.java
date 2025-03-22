package com.immfly.payments.infrastructure.dto;

import com.immfly.payments.domain.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Order")
public record OrderDTO(Long id,
                       List<ProductDTO> products,
                       BigDecimal totalPrice,
                       String seatLetter,
                       Integer seatNumber,
                       String buyerEmail) {

    public static OrderDTO fromDomain(Order order) {
        var products = order.products()
            .stream()
            .map(ProductDTO::fromDomain)
            .toList();
        return new OrderDTO(order.id(),
            products,
            order.totalPrice(),
            order.seatLetter(),
            order.seatNumber(),
            order.buyerEmail());
    }
}
