package com.organizas.organizas;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ValidationTestConfig {
    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
