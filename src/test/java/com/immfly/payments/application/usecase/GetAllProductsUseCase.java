package com.immfly.payments.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.repository.ProductRepository;
import com.immfly.payments.infrastructure.dto.ProductDTO;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GetAllProductsUseCase")
class GetAllProductsUseCaseTest {

    @Test
    void shouldReturnAllProducts() {
        ProductRepository repo = mock(ProductRepository.class);
        List<Product> products = List.of(mock(Product.class));
        when(repo.findAll()).thenReturn(products);

        GetAllProductsUseCase useCase = new GetAllProductsUseCase(repo);

        List<ProductDTO> result = useCase.execute();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }
}
