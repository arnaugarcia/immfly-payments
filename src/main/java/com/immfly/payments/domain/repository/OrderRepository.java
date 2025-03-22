package com.immfly.payments.domain.repository;

import com.immfly.payments.domain.model.Order;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
