package com.example.cosmocats.security;

import com.example.cosmocats.aspect.ApiKeyFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApiKeyFilterTest {

    private ApiKeyFilter apiKeyFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        apiKeyFilter = new ApiKeyFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
    }

    @Test
    void whenValidApiKey_thenProceed() throws ServletException, IOException {
        request.addHeader("X-API-KEY", "cosmo-cat-api-key");

        apiKeyFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void whenInvalidApiKey_thenForbidden() throws ServletException, IOException {
        request.addHeader("X-API-KEY", "invalid-key");

        apiKeyFilter.doFilterInternal(request, response, filterChain);

        assertEquals(403, response.getStatus());
        assertEquals("Invalid API Key", response.getContentAsString());
    }

    @Test
    void whenNoApiKey_thenForbidden() throws ServletException, IOException {
        apiKeyFilter.doFilterInternal(request, response, filterChain);

        assertEquals(403, response.getStatus());
        assertEquals("Invalid API Key", response.getContentAsString());
    }
}
