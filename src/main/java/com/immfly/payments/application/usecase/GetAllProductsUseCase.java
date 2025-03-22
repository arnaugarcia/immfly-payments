package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.repository.ProductRepository;
import com.immfly.payments.infrastructure.dto.ProductDTO;
import java.util.List;

public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> execute() {
        return productRepository.findAll()
            .stream()
            .map(ProductDTO::fromDomain)
            .toList();
    }
}
