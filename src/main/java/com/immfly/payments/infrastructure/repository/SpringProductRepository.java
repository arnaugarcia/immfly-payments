package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProductRepository extends JpaRepository<Product, Long>, ProductRepository {
}
