package com.example.lab41.controller;

import com.example.lab41.dto.ProductResponse;
import com.example.lab41.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService service;

    @MockBean
    CommandLineRunner seed;

    @Test
    void create_shouldReturn201() throws Exception {
        when(service.create(any())).thenReturn(
            new ProductResponse("p1", "Keyboard", new BigDecimal("120"), 10, "c1", List.of())
        );
        mvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Keyboard\",\"price\":120,\"stock\":10,\"categoryId\":\"c1\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/products/p1"));
    }
}
