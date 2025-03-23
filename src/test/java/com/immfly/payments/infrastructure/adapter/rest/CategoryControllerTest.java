package com.immfly.payments.infrastructure.adapter.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.immfly.payments.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@DisplayName("CategoryController")
class CategoryControllerTest extends BaseIT {

    private static final String API_CATEGORIES = "/api/categories";

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /api/categories")
    class GetAllCategories {

        @Test
        @WithMockUser(username = "username")
        void shouldReturnCategoryList() throws Exception {
            mockMvc.perform(get(API_CATEGORIES)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
    }
}
