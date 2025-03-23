package com.immfly.payments.domain.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.immfly.payments.domain.exception.DomainException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Order")
class OrderTest {

    private static Product sampleProduct(BigDecimal price) {
        return new Product(1L, "Product", price, new Category(1L, "Test", null), "img.png");
    }

    @Nested
    @DisplayName("Constructor Validation")
    class ConstructorTests {

        @Test
        void shouldCreateOrderCorrectly() {
            Order order = new Order(1L, "A", 1);
            assertEquals("A", order.seatLetter());
            assertEquals(1, order.seatNumber());
            assertEquals(BigDecimal.ZERO, order.totalPrice());
            assertEquals(OrderStatus.OPEN, order.status());
        }

        @Test
        void shouldThrowIfSeatLetterIsNull() {
            DomainException ex = assertThrows(DomainException.class, () -> new Order(1L, null, 5));
            assertEquals("Seat letter is required", ex.getMessage());
        }

        @Test
        void shouldThrowIfSeatLetterIsBlank() {
            DomainException ex = assertThrows(DomainException.class, () -> new Order(1L, " ", 5));
            assertEquals("Seat letter is required", ex.getMessage());
        }

        @Test
        void shouldThrowIfSeatNumberIsInvalid() {
            DomainException ex = assertThrows(DomainException.class, () -> new Order(1L, "A", 0));
            assertEquals("Seat number must be positive", ex.getMessage());
        }

        @Test
        void shouldCreateOrderViaFactoryMethod() {
            Order order = Order.create("C", 3);
            assertNull(order.id());
            assertEquals("C", order.seatLetter());
            assertEquals(3, order.seatNumber());
        }
    }

    @Nested
    @DisplayName("Product Handling")
    class ProductTests {

        @Test
        void shouldAddProductAndUpdateTotal() {
            Order order = new Order(1L, "B", 2);
            order.addProduct(sampleProduct(new BigDecimal("10.00")));
            order.addProduct(sampleProduct(new BigDecimal("5.50")));

            assertEquals(2, order.products().size());
            assertEquals(new BigDecimal("15.50"), order.totalPrice());
        }
    }

    @Nested
    @DisplayName("Buyer Email")
    class EmailTests {

        @Test
        void shouldUpdateBuyerEmail() {
            Order order = new Order(1L, "C", 7);
            order.update("user@example.com");
            assertEquals("user@example.com", order.buyerEmail());
        }
    }

    @Nested
    @DisplayName("Status Transitions")
    class StatusTests {

        @Test
        void shouldCancelOrder() {
            Order order = new Order(1L, "D", 4);
            order.cancel();
            assertEquals(OrderStatus.CANCELED, order.status());
        }

        @Test
        void shouldDropOrder() {
            Order order = new Order(1L, "E", 5);
            order.dropped();
            assertEquals(OrderStatus.DROPPED, order.status());
        }

        @Test
        void shouldFinishOrder() {
            Order order = new Order(1L, "F", 6);
            Payment payment = new Payment(1L, "8iufuhhd2389x", PaymentGatewayType.AYDEN, PaymentStatus.PAID,
                LocalDateTime.now());
            order.finishOrder(payment);

            assertEquals(OrderStatus.FINISHED, order.status());
            assertEquals(payment, order.payment());
        }
    }
}
