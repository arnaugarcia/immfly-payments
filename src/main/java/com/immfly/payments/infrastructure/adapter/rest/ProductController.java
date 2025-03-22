package com.immfly.payments.infrastructure.adapter.rest;


import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Operation(summary = "Retrieve all products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
