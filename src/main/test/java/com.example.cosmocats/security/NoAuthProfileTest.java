package com.example.cosmocats.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("no-auth")
public class NoAuthProfileTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenNoAuthProfile_thenEndpointsShouldBeAccessible() {
        webTestClient.get().uri("/api/products")
            .exchange()
            .expectStatus().isOk();
    }
}
