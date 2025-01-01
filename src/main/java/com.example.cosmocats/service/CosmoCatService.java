package main.java.com.example.cosmocats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CosmoCatService {

    private final FeatureToggleService featureToggleService;

    @Autowired
    public CosmoCatService(FeatureToggleService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    public String getCosmoCats() {
        if (!featureToggleService.isCosmoCatsEnabled()) {
            throw new FeatureNotAvailableException("Cosmo Cats feature is disabled.");
        }
        // If the flag is enabled, return a string of data for the demonstration
        return "Cosmo Cats data!";
    }
}
