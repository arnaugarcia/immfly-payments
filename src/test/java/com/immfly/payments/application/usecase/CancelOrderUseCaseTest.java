package com.immfly.payments.application.usecase;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CancelOrderUseCase")
class CancelOrderUseCaseTest {

    private CancelOrderUseCase cancelOrderUseCase;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        cancelOrderUseCase = new CancelOrderUseCase(orderRepository);
    }

    @Nested
    @DisplayName("cancel")
    class Cancel {

        @Test
        void shouldCancelOrderIfExists() {
            Long id = new Random().nextLong();
            Order order = mock(Order.class);

            when(orderRepository.findById(id)).thenReturn(Optional.of(order));

            cancelOrderUseCase.cancel(id);

            verify(order).cancel();
            verify(orderRepository).save(order);
        }

        @Test
        void shouldThrowWhenOrderNotFound() {
            Long id = new Random().nextLong();
            when(orderRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () -> cancelOrderUseCase.cancel(id));
        }
    }
}
