package main.test.java.com.example.cosmocats.service;

import com.example.cosmocats.exception.FeatureNotAvailableException;

import main.java.com.example.cosmocats.service.CosmoCatService;
import main.java.com.example.cosmocats.service.FeatureToggleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CosmoCatServiceTest {

    @Mock
    private FeatureToggleService featureToggleService;

    @InjectMocks
    private CosmoCatService cosmoCatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCosmoCats_ShouldReturnData_WhenFeatureEnabled() {
        // Mock enable flag for the cosmoCats function
        when(featureToggleService.isCosmoCatsEnabled()).thenReturn(true);

        // Check that the method returns data when the flag is enabled
        String result = cosmoCatService.getCosmoCats();

        assertNotNull(result);
        assertEquals("Cosmo Cats data!", result);
    }

    @Test
    void getCosmoCats_ShouldThrowFeatureNotAvailableException_WhenFeatureDisabled() {
        // Mock flag deactivation for the cosmoCats function
        when(featureToggleService.isCosmoCatsEnabled()).thenReturn(false);

        // Check that the method generates an exception when the flag is off
        assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
