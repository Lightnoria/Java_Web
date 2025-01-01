package com.example.cosmocats.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerSecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void accessProductWithValidApiKey() {
        webTestClient.get().uri("/api/products/1")
            .header("X-API-KEY", "cosmo-cat-api-key")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void accessProductWithoutApiKey() {
        webTestClient.get().uri("/api/products/1")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void accessProductWithInvalidApiKey() {
        webTestClient.get().uri("/api/products/1")
            .header("X-API-KEY", "invalid-key")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }
}
