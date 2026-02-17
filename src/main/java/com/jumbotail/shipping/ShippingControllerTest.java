package com.jumbotail.shipping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ShippingControllerTest {
    @Autowired private MockMvc mockMvc;

    @Test
    void testNearestWarehouse() throws Exception {
        mockMvc.perform(get("/api/v1/warehouse/nearest").param("sellerId", "123").param("productId", "456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.warehouseId").exists());
    }

    @Test
    void testInvalidSeller() throws Exception {
        mockMvc.perform(get("/api/v1/warehouse/nearest").param("sellerId", "invalid").param("productId", "456"))
                .andExpect(status().isBadRequest());
    }

}
