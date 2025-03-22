package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.infrastructure.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpringStockRepository extends JpaRepository<StockEntity, Long> { }
