package com.immfly.payments.infrastructure.mapper;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.infrastructure.entity.OrderEntity;
import com.immfly.payments.infrastructure.entity.OrderStatusEntity;
import java.util.stream.Collectors;

public class OrderMapper {


    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;
        OrderEntity entity = new OrderEntity();
        entity.setId(order.id());
        entity.setSeatLetter(order.seatLetter());
        entity.setSeatNumber(order.seatNumber());
        entity.setTotalPrice(order.totalPrice());
        // Convert domain enum to persistence enum
        entity.setStatus(OrderStatusEntity.fromDomain(order.status()));
        if (order.products() != null) {
            entity.setProducts(order.products().stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList()));
        }
        return entity;
    }

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        Order order = new Order(entity.getId(), entity.getSeatLetter(), entity.getSeatNumber());
        order.totalPrice(entity.getTotalPrice());
        // Convert persistence enum back to domain enum
        order.status(entity.getStatus().toDomain());
        if (entity.getProducts() != null) {
            order.setProducts(entity.getProducts().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList()));
        }
        return order;
    }
}
