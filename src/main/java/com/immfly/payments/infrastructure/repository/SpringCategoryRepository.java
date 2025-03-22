package com.immfly.payments.infrastructure.repository;

import com.immfly.payments.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringCategoryRepository extends JpaRepository<CategoryEntity, Long> {}
