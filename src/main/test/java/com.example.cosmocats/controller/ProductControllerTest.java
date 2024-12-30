package com.example.cosmocats.controller;

import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts_ShouldReturnProductList() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Galaxy Ball");
        productDTO.setDescription("A shiny ball for space cats");
        productDTO.setPrice(15.99);

        when(productService.getAllProducts()).thenReturn(List.of(productDTO));

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Galaxy Ball"));
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Galaxy Ball");
        productDTO.setDescription("A shiny ball for space cats");
        productDTO.setPrice(15.99);

        when(productService.createProduct(any())).thenReturn(productDTO);

        String json = """
            {
                "name": "Galaxy Ball",
                "description": "A shiny ball for space cats",
                "price": 15.99
            }
        """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Galaxy Ball"));
    }
}
