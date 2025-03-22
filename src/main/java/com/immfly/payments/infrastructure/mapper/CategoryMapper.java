package com.immfly.payments.infrastructure.mapper;

import com.immfly.payments.domain.model.Category;
import com.immfly.payments.infrastructure.entity.CategoryEntity;

public class CategoryMapper {

    private CategoryMapper() {}

    public static CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.id());
        entity.setName(category.name());
        entity.setParent(toEntity(category.parent()));
        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Category(entity.getId(), entity.getName(), toDomain(entity.getParent()));
    }
}
