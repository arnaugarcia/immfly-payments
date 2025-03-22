package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.domain.model.Category;
import com.immfly.payments.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {}
