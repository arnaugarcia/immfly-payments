package com.immfly.payments.infrastructure.adapter.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.immfly.payments.infrastructure.adapter.stock.MockStockService;
import com.immfly.payments.infrastructure.entity.CategoryEntity;
import com.immfly.payments.infrastructure.entity.OrderEntity;
import com.immfly.payments.infrastructure.entity.OrderStatusEntity;
import com.immfly.payments.infrastructure.entity.ProductEntity;
import com.immfly.payments.infrastructure.repository.SpringCategoryRepository;
import com.immfly.payments.infrastructure.repository.SpringOrderRepository;
import com.immfly.payments.infrastructure.repository.SpringProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DisplayName("OrderController")
class OrderControllerTest {

    private static final String API_ORDERS = "/api/orders";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringOrderRepository springOrderRepository;

    @Autowired
    private SpringProductRepository springProductRepository;

    @Autowired
    private SpringCategoryRepository springCategoryRepository;

    @MockitoSpyBean
    private MockStockService mockStockService;

    private static final String PRODUCT_NAME = "Product 1";
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal("10.00");
    private static final String PRODUCT_IMAGE = "image1.jpg";

    private ProductEntity product;

    private static final String SEAT_LETTER = "A";
    private static final Integer SEAT_NUMBER = 1;
    private static final BigDecimal TOTAL_PRICE = PRODUCT_PRICE;
    private static final OrderStatusEntity STATUS = OrderStatusEntity.OPEN;

    private OrderEntity order;

    @BeforeEach
    void setUp() {
        springOrderRepository.deleteAll();
        springProductRepository.deleteAll();
        springCategoryRepository.deleteAll();

        CategoryEntity category = new CategoryEntity();
        category.setName("Category 1");
        springCategoryRepository.save(category);

        product = new ProductEntity();
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setImage(PRODUCT_IMAGE);

        product.setCategory(category);
        product = springProductRepository.save(product);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSeatLetter(SEAT_LETTER);
        orderEntity.setSeatNumber(SEAT_NUMBER);
        orderEntity.setTotalPrice(TOTAL_PRICE);
        orderEntity.setStatus(STATUS);
        orderEntity.setProducts(List.of(product));
        order = springOrderRepository.save(orderEntity);
    }

    @Nested
    @DisplayName("POST /api/orders")
    class CreateOrder {

        @Test
        @WithMockUser
        void shouldCreateOrder() throws Exception {
            mockMvc.perform(post(API_ORDERS)
                    .param("seatLetter", "B")
                    .param("seatNumber", "12"))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("DELETE /api/orders/{orderId}")
    class CancelOrder {

        @Test
        @WithMockUser
        void shouldReturn404WhenOrderNotFound() throws Exception {
            mockMvc.perform(delete(API_ORDERS + "/" + getRandomId()))
                .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        void shouldCancelOrder() throws Exception {
            mockMvc.perform(delete(API_ORDERS + "/" + order.getId()))
                .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("PUT /api/orders/{orderId}/products/{productId}")
    class AddProduct {

        @Test
        @WithMockUser
        void shouldReturn404WhenOrderOrProductNotFound() throws Exception {
            mockMvc.perform(put(API_ORDERS + "/" + getRandomId() + "/products/" + getRandomId()))
                .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        void shouldAddProductToOrder() throws Exception {
            mockMvc.perform(put(API_ORDERS + "/" + order.getId() + "/products/" + product.getId()))
                .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        void shouldReturn404WhenProductNotFound() throws Exception {
            mockMvc.perform(put(API_ORDERS + "/" + order.getId() + "/products/" + getRandomId()))
                .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        void shouldReturnConflictWhenProductDoesntHaveStock() throws Exception {
            when(mockStockService.hasStock(product.getId())).thenReturn(false);
            mockMvc.perform(put(API_ORDERS + "/" + order.getId() + "/products/" + product.getId()))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/orders/{orderId}")
    class FinishOrder {

        @Test
        @WithMockUser
        void shouldReturn404WhenOrderNotFound() throws Exception {
            int randomId = getRandomId();
            mockMvc.perform(post(API_ORDERS + "/" + randomId)
                    .param("cardToken", "tok_test")
                    .param("gateway", "MOCK"))
                .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        void shouldReturn400WhenCardTokenIsMissing() throws Exception {
            mockMvc.perform(post(API_ORDERS + "/" + 1L)
                    .param("gateway", "MOCK"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser
        void shouldReturn400WhenGatewayIsMissing() throws Exception {
            mockMvc.perform(post(API_ORDERS + "/" + 1L)
                    .param("cardToken", "tok_test"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser
        void shouldReturn400WhenGatewayIsInvalid() throws Exception {
            mockMvc.perform(post(API_ORDERS + "/" + order.getId())
                    .param("cardToken", "tok_test")
                    .param("gateway", "INVALID"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser
        void shouldFinishOrder() throws Exception {
            mockMvc.perform(post(API_ORDERS + "/" + order.getId())
                    .param("cardToken", "tok_test")
                    .param("gateway", "MOCK"))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("PUT /api/orders/{orderId}")
    class UpdateOrder {

        @Test
        @WithMockUser
        void shouldReturn404WhenOrderNotFound() throws Exception {
            mockMvc.perform(put(API_ORDERS + "/" + getRandomId())
                    .contentType("application/json")
                    .content(
                        """
                        {
                            "buyerEmail": "newemail@buyer.com"
                        }
                        """
                    ))
                .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        void shouldUpdateOrder() throws Exception {
            var emailToUpdate = "newemail@buyer.com";
            mockMvc.perform(put(API_ORDERS + "/" + order.getId())
                .contentType("application/json")
                .content(
                """
                {
                    "buyerEmail": "newemail@buyer.com"
                }
                """
            )).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.buyerEmail").value(emailToUpdate))
                .andExpect(jsonPath("$.seatLetter").value(SEAT_LETTER))
                .andExpect(jsonPath("$.seatNumber").value(SEAT_NUMBER))
                .andExpect(jsonPath("$.totalPrice").value(TOTAL_PRICE.intValue()));

            OrderEntity updatedOrder = springOrderRepository.findById(order.getId()).orElseThrow();
            assertThat(updatedOrder.getBuyerEmail()).isEqualTo(emailToUpdate);
            assertThat(updatedOrder.getId()).isEqualTo(order.getId());
            assertThat(updatedOrder.getTotalPrice()).isEqualTo(TOTAL_PRICE.toString());
            assertThat(updatedOrder.getSeatLetter()).isEqualTo(SEAT_LETTER);
            assertThat(updatedOrder.getSeatNumber()).isEqualTo(SEAT_NUMBER);
        }
    }

    private static int getRandomId() {
        return new Random().nextInt();
    }
}
