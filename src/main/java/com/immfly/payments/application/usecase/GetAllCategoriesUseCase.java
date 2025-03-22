package com.immfly.payments.application.usecase;

import com.immfly.payments.domain.model.Category;
import com.immfly.payments.domain.repository.CategoryRepository;
import com.immfly.payments.infrastructure.dto.CategoryDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public GetAllCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> execute() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    private CategoryDTO mapToDTO(Category category) {
        Long parentId = category.parent() != null ? category.parent().id() : null;
        return new CategoryDTO(category.id(), category.name(), parentId);
    }
}
