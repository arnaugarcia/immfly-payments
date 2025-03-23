package com.immfly.payments.application.usecase;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.immfly.payments.domain.model.Category;
import com.immfly.payments.domain.repository.CategoryRepository;
import com.immfly.payments.infrastructure.dto.CategoryDTO;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GetAllCategoriesUseCase")
class GetAllCategoriesUseCaseTest {

    @Test
    void shouldReturnAllCategories() {
        CategoryRepository repo = mock(CategoryRepository.class);
        List<Category> categories = List.of(mock(Category.class));
        when(repo.findAll()).thenReturn(categories);

        GetAllCategoriesUseCase useCase = new GetAllCategoriesUseCase(repo);

        List<CategoryDTO> result = useCase.execute();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }
}
