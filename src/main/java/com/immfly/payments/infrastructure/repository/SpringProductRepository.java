package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProductRepository extends JpaRepository<ProductEntity, Long> {
}
