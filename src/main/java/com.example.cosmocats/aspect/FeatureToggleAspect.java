package main.java.com.example.cosmocats.aspect;

import com.example.cosmocats.service.FeatureToggleService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FeatureToggleAspect {

    @Autowired
    private FeatureToggleService featureToggleService;

    @Before("execution(* com.example.cosmocats.service.ProductService.getAllProducts(..))")
    public void checkKittyProductsFeature() {
        if (!featureToggleService.isKittyProductsEnabled()) {
            throw new FeatureNotAvailableException("Kitty Products feature is disabled.");
        }
    }
}
