package com.example.cosmocats.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void securedEndpointWithoutApiKeyShouldBeForbidden() {
        webTestClient.get().uri("/api/products")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void unsecuredEndpointShouldBeAccessible() {
        webTestClient.get().uri("/public-endpoint")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void securedEndpointWithValidApiKeyShouldBeAccessible() {
        webTestClient.get().uri("/api/products")
            .header(HttpHeaders.AUTHORIZATION, "Bearer cosmo-cat-api-key")
            .exchange()
            .expectStatus().isOk();
    }
}
