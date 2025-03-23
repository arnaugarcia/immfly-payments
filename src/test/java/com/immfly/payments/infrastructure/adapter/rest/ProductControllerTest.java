package com.immfly.payments.infrastructure.adapter.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.immfly.payments.domain.model.Order;
import com.immfly.payments.domain.model.Product;
import com.immfly.payments.domain.repository.OrderRepository;
import com.immfly.payments.infrastructure.entity.CategoryEntity;
import com.immfly.payments.infrastructure.entity.ProductEntity;
import com.immfly.payments.infrastructure.repository.SpringCategoryRepository;
import com.immfly.payments.infrastructure.repository.SpringOrderRepository;
import com.immfly.payments.infrastructure.repository.SpringProductRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DisplayName("ProductController")
class ProductControllerTest {

    private static final String API_PRODUCTS = "/api/products";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringProductRepository springProductRepository;

    @Autowired
    private SpringCategoryRepository springCategoryRepository;

    private final String PRODUCT_NAME = "Product 1";
    private final BigDecimal PRODUCT_PRICE = new BigDecimal("10.0");
    private final String PRODUCT_IMAGE = "image1.jpg";

    @BeforeEach
    void setUp() {
        springProductRepository.deleteAll();
        springCategoryRepository.deleteAll();

        ProductEntity product = new ProductEntity();
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setImage(PRODUCT_IMAGE);

        CategoryEntity category = new CategoryEntity();
        category.setName("Category 1");
        springCategoryRepository.save(category);

        product.setCategory(category);
        springProductRepository.save(product);
    }

    @Nested
    @DisplayName("GET /api/products")
    class GetAllProducts {

        @Test
        @WithMockUser
        void shouldReturnProductList() throws Exception {
            mockMvc.perform(get(API_PRODUCTS)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(PRODUCT_NAME))
                .andExpect(jsonPath("$[0].price").value(PRODUCT_PRICE))
                .andExpect(jsonPath("$[0].image").value(PRODUCT_IMAGE));
        }

        @Test
        void shouldReturnForbidden() throws Exception {
            mockMvc.perform(get(API_PRODUCTS)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        }

    }
}
