package com.organizas.organizas.validation;

import com.organizas.organizas.dto.request.LoginRequestDto;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginRequestDtoValidationTest extends ValidationTestBase {

    @Autowired
    private Validator validator;

    @Test
    void shouldFailWhenEmailIsBlank() {
        var dto = new LoginRequestDto("", "12345678");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail é obrigatório")));
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        var dto = new LoginRequestDto("teste", "12345678");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Insira um e-mail válido")));
    }

    @Test
    void shouldFailWhenPasswordIsBlank() {
        var dto = new LoginRequestDto("teste@email.com", "");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Senha é obrigatório")));
    }

    @Test
    void shouldPassWhenBodyIsValid() {
        var dto = new LoginRequestDto("teste@email.com", "12345678");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}
