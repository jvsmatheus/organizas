package com.organizas.organizas.validation;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserRequestDtoValidationTest extends ValidationTestBase {

    @Autowired
    private Validator validator;

    @Test
    void shouldFailWhenNameIsBlank() {
        var dto = new CreateUserRequestDto("", "teste@email.com", "Teste@123");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome é obrigatório")));
    }

    @Test
    void shouldFailWhenEmailIsBlank() {
        var dto = new CreateUserRequestDto("Teste", "", "Teste@123");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail é obrigatório")));
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        var dto = new CreateUserRequestDto("Teste", "teste", "Teste@123");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Insira um e-mail válido")));
    }

    @Test
    void shouldFailWhenPassWordIsInvalid() {
        var dto = new CreateUserRequestDto("Teste", "teste", "Teste@123");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Insira um e-mail válido")));
    }
}
