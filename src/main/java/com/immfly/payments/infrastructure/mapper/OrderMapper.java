package com.immfly.payments.infrastructure.mapper;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.infrastructure.entity.OrderEntity;
import com.immfly.payments.infrastructure.entity.OrderStatusEntity;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;
        OrderEntity entity = new OrderEntity();
        entity.setId(order.id());
        entity.setSeatLetter(order.seatLetter());
        entity.setSeatNumber(order.seatNumber());
        entity.setTotalPrice(order.totalPrice());
        entity.setStatus(OrderStatusEntity.fromDomain(order.status()));
        if (order.products() != null) {
            entity.setProducts(order.products().stream()
                .map(ProductMapper::toEntity)
                .toList());
        }
        return entity;
    }

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        Order order = new Order(entity.getSeatLetter(), entity.getSeatNumber());
        Payment payment = order.payment();
        switch (entity.getStatus()) {
            case CANCELED -> order.cancel();
            case FINISHED -> order.finishOrder(payment);
            case DROPPED -> order.dropped();
            case OPEN -> order.status();
        }
        if (entity.getProducts() != null) {
            entity.getProducts().stream()
                .map(ProductMapper::toDomain)
                .toList()
                .forEach(order::addProduct);
        }
        return order;
    }
}
