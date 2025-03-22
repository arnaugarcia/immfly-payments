package com.immfly.payments.domain.repository;


import com.immfly.payments.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
}
