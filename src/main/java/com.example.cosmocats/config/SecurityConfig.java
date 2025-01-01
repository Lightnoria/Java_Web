package com.example.cosmocats.config;

import com.example.cosmocats.aspect.ApiKeyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(auth -> auth
                .antMatchers("/api/products/**").authenticated() // Захищаємо /products
                .anyRequest().permitAll()
            )
            .addFilterBefore(new ApiKeyFilter(), UsernamePasswordAuthenticationFilter.class); // Додаємо кастомний фільтр

        return http.build();
    }
}
