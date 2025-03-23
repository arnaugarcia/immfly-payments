package com.immfly.payments.application.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.model.StockService;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.domain.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UpdateOrderUseCase")
class UpdateOrderUseCaseTest {

    private UpdateOrderUseCase useCase;
    private OrderRepository orderRepo;
    private ProductRepository productRepo;
    private StockService stockService;

    @BeforeEach
    void setUp() {
        orderRepo = mock(OrderRepository.class);
        productRepo = mock(ProductRepository.class);
        stockService = mock(StockService.class);
        useCase = new UpdateOrderUseCase(orderRepo, productRepo, stockService);
    }

    @Test
    void shouldAddProductToOrder() {
        Long orderId = new Random().nextLong();
        Long productId = new Random().nextLong();

        Order order = mock(Order.class);
        when(order.products()).thenReturn(List.of());
        Product product = mock(Product.class);

        when(orderRepo.findById(orderId)).thenReturn(Optional.of(order));
        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(stockService.hasStock(product.id())).thenReturn(true);
        when(orderRepo.save(order)).thenReturn(order);

        useCase.addProductToOrder(orderId, productId);

        verify(order).addProduct(product);
        verify(orderRepo).save(order);
    }

    @Test
    void shouldThrowIfOrderNotFound() {
        Long orderId = new Random().nextLong();
        Long productId = new Random().nextLong();

        when(orderRepo.findById(any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> useCase.addProductToOrder(orderId, productId));
    }

    @Test
    void shouldThrowIfProductNotFound() {
        Long orderId = new Random().nextLong();
        Long productId = new Random().nextLong();
        when(orderRepo.findById(orderId)).thenReturn(Optional.of(mock(Order.class)));
        when(productRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> useCase.addProductToOrder(orderId, productId));
    }
}
