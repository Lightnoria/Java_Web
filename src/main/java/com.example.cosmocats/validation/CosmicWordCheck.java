package com.example.cosmocats.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CosmicWordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWordCheck {
    String message() default "Name must contain a cosmic term (e.g., 'star', 'galaxy', 'comet')";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
