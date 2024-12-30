package main.test.java.com.example.cosmocats.service;

import com.example.cosmocats.exception.FeatureNotAvailableException;
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
        // Мокируем включение флага для функции cosmoCats
        when(featureToggleService.isCosmoCatsEnabled()).thenReturn(true);

        // Проверяем, что метод возвращает данные, когда флаг включен
        String result = cosmoCatService.getCosmoCats();

        assertNotNull(result);
        assertEquals("Cosmo Cats data!", result);
    }

    @Test
    void getCosmoCats_ShouldThrowFeatureNotAvailableException_WhenFeatureDisabled() {
        // Мокируем выключение флага для функции cosmoCats
        when(featureToggleService.isCosmoCatsEnabled()).thenReturn(false);

        // Проверяем, что метод генерирует исключение, когда флаг выключен
        assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
