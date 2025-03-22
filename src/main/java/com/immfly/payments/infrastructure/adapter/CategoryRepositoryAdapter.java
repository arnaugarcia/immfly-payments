package com.immfly.payments.infrastructure.adapter;

import com.immfly.payments.domain.model.Category;
import com.immfly.payments.domain.repository.CategoryRepository;
import com.immfly.payments.infrastructure.entity.CategoryEntity;
import com.immfly.payments.infrastructure.mapper.CategoryMapper;
import com.immfly.payments.infrastructure.repository.SpringCategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final SpringCategoryRepository springCategoryRepository;

    public CategoryRepositoryAdapter(SpringCategoryRepository springCategoryRepository) {
        this.springCategoryRepository = springCategoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> entities = springCategoryRepository.findAll();
        return entities.stream()
            .map(CategoryMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        CategoryEntity saved = springCategoryRepository.save(entity);
        return CategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return springCategoryRepository.findById(id)
            .map(CategoryMapper::toDomain);
    }
}
