package com.immfly.payments.application.usecase;


import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.dto.OrderDTO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CreateOrderUseCase")
class CreateOrderUseCaseTest {

    private CreateOrderUseCase createOrderUseCase;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        createOrderUseCase = new CreateOrderUseCase(orderRepository);
    }

    @Nested
    @DisplayName("createOrder")
    class CreateOrder {

        @Test
        void shouldCreateAndSaveOrder() {
            Order savedOrder = mock(Order.class);
            when(orderRepository.save(any())).thenReturn(savedOrder);

            OrderDTO result = createOrderUseCase.createOrder("A", 10);

            assertNotNull(result);
            verify(orderRepository).save(any());
        }

        @Test
        void shouldThrowIfSeatInvalid() {
            assertThrows(Exception.class, () -> createOrderUseCase.createOrder(null, 0));
        }
    }
}
