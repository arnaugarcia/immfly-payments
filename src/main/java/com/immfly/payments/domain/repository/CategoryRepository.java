package com.immfly.payments.domain.repository;


import com.immfly.payments.domain.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
}
