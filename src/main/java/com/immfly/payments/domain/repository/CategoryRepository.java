package com.immfly.payments.domain.repository;


import com.immfly.payments.domain.model.Category;
import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
}
