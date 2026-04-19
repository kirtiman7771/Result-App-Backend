package com.cts.ResultApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * General application configuration for shared beans.
 * Separating PasswordEncoder here prevents circular dependencies in SecurityConfig.
 */
@Configuration
public class AppConfig {

    /**
     * Defines the BCrypt password encoder bean.
     * @return a new BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
