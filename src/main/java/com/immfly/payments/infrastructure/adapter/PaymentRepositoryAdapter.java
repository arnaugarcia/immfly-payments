package com.immfly.payments.infrastructure.adapter;

import com.immfly.payments.domain.model.Payment;
import com.immfly.payments.domain.repository.PaymentRepository;
import com.immfly.payments.infrastructure.entity.PaymentEntity;
import com.immfly.payments.infrastructure.mapper.PaymentMapper;
import com.immfly.payments.infrastructure.repository.SpringPaymentRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final SpringPaymentRepository springPaymentRepository;

    public PaymentRepositoryAdapter(SpringPaymentRepository springPaymentRepository) {
        this.springPaymentRepository = springPaymentRepository;
    }

    @Override
    public Payment findById(Long id) {
        Optional<PaymentEntity> entity = springPaymentRepository.findById(id);
        return entity.map(PaymentMapper::toDomain).orElse(null);
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = PaymentMapper.toEntity(payment);
        PaymentEntity saved = springPaymentRepository.save(entity);
        return PaymentMapper.toDomain(saved);
    }
}
