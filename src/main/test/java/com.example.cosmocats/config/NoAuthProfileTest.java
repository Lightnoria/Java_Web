package com.example.cosmocats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("noauth")
public class NoAuthProfileTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void whenNoAuthProfile_thenEndpointsAreAccessible() {
        String response = restTemplate.getForObject("/api/products", String.class);
        assertNotNull(response);
    }
}
