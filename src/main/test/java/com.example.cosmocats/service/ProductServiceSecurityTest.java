package com.example.cosmocats.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
public class ProductServiceSecurityTest {

    @Autowired
    private ProductService productService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenAdmin_thenCanCreateProduct() {
        productService.createProduct(null); // Test logic here
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUser_thenCannotCreateProduct() {
        assertThrows(AccessDeniedException.class, () -> productService.createProduct(null));
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUser_thenCanViewProducts() {
        productService.getAllProducts();
    }
}
