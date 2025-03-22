package com.immfly.payments.infrastructure.adapter.rest;


import com.immfly.payments.application.usecase.GetAllProductsUseCase;
import com.immfly.payments.infrastructure.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final GetAllProductsUseCase getAllProductsUseCase;

    @Operation(summary = "Retrieve all products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(getAllProductsUseCase.execute());
    }

}
