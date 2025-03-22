package com.immfly.payments.infrastructure.adapter;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.repository.ProductRepository;
import com.immfly.payments.infrastructure.entity.ProductEntity;
import com.immfly.payments.infrastructure.mapper.ProductMapper;
import com.immfly.payments.infrastructure.repository.SpringProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringProductRepository springProductRepository;

    public ProductRepositoryAdapter(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = springProductRepository.findAll();
        return entities.stream().map(ProductMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> entity = springProductRepository.findById(id);
        return entity.map(ProductMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = springProductRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }
}
