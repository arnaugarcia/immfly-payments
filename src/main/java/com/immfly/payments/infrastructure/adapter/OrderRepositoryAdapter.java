package com.immfly.payments.infrastructure.adapter;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.entity.OrderEntity;
import com.immfly.payments.infrastructure.mapper.OrderMapper;
import com.immfly.payments.infrastructure.repository.SpringOrderRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final SpringOrderRepository springOrderRepository;

    public OrderRepositoryAdapter(SpringOrderRepository springOrderRepository) {
        this.springOrderRepository = springOrderRepository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> entity = springOrderRepository.findById(id);
        return entity.map(OrderMapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity saved = springOrderRepository.save(entity);
        return OrderMapper.toDomain(saved);
    }
}
