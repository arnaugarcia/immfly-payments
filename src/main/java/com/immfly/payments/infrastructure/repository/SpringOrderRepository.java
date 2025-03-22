package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringOrderRepository extends JpaRepository<Order, Long>, OrderRepository {
}
