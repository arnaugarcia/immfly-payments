package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringOrderRepository extends JpaRepository<OrderEntity, Long> {
}
