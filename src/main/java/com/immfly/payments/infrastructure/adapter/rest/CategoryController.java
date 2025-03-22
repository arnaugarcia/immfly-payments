package com.immfly.payments.infrastructure.adapter.rest;

import com.immfly.payments.application.usecase.GetAllCategoriesUseCase;
import com.immfly.payments.domain.model.Category;
import com.immfly.payments.domain.repository.CategoryRepository;
import com.immfly.payments.infrastructure.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {

    private final GetAllCategoriesUseCase getAllCategoriesUseCase;

    @Operation(summary = "Retrieve all categories", description = "Returns a list of all categories available.")
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return getAllCategoriesUseCase.execute();
    }
}
