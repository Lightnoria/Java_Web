package com.example.cosmocats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    @Profile("default")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .and()
            .oauth2ResourceServer().jwt()
            .and().build();
    }

    @Bean
    @Profile("no-auth")
    public SecurityFilterChain noAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
            .anyRequest().permitAll()
            .and().build();
    }
}
