package com.example.cosmocats.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private final List<String> cosmicTerms = List.of("star", "galaxy", "comet", "planet", "cosmos");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return cosmicTerms.stream().anyMatch(value.toLowerCase()::contains);
    }
}
