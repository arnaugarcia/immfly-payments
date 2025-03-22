package com.immfly.payments.infrastructure.mapper;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.infrastructure.entity.ProductEntity;

public class ProductMapper {

    private ProductMapper() {}

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;
        ProductEntity entity = new ProductEntity();
        entity.setId(product.id());
        entity.setName(product.name());
        entity.setPrice(product.price());
        entity.setImage(product.image());
        entity.setCategory(CategoryMapper.toEntity(product.category()));
        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            CategoryMapper.toDomain(entity.getCategory()),
            entity.getImage()
        );
    }
}
