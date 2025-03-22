package com.immfly.payments.infrastructure.dto;

import com.immfly.payments.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Product data transfer object")
public record ProductDTO(
    @Schema(description = "Unique identifier of the product", example = "1") Long id,
    @Schema(description = "Name of the product", example = "Laptop") String name,
    @Schema(description = "Price of the product", example = "999.99") BigDecimal price,
    @Schema(description = "Category of the product", example = "Electronics") String category,
    @Schema(description = "Image URL of the product", example = "http://example.com/image.jpg") String image) {

    public static ProductDTO fromDomain(Product product) {
        return new ProductDTO(product.id(), product.name(), product.price(), product.category().name(), product.image());
    }
}

