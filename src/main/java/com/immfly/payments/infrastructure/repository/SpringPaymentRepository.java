package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
