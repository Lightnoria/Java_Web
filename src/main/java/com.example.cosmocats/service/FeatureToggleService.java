package main.java.com.example.cosmocats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    @Value("${feature.cosmoCats.enabled}")
    private boolean cosmoCatsEnabled;

    @Value("${feature.kittyProducts.enabled}")
    private boolean kittyProductsEnabled;

    public boolean isCosmoCatsEnabled() {
        return cosmoCatsEnabled;
    }

    public boolean isKittyProductsEnabled() {
        return kittyProductsEnabled;
    }
}
