package com.organizas.organizas.validation;

import com.organizas.organizas.dto.request.LoginRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginRequestDtoValidationTest extends ValidationTestBase {

    @Autowired
    private Validator validator;

    @ParameterizedTest
    @MethodSource("invalidLoginRequests")
    void shouldFailValidation(LoginRequestDto dto, String expectedMessage) {
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());

        var messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertTrue(messages.contains(expectedMessage));
    }

    @Test
    void shouldPassWhenBodyIsValid() {
        var dto = new LoginRequestDto("teste@email.com", "Teste@123");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    static Stream<Arguments> invalidLoginRequests() {
        return Stream.of(
                Arguments.of(
                        new LoginRequestDto("", "Teste@123"),
                        "E-mail é obrigatório"
                ),
                Arguments.of(
                        new LoginRequestDto("teste", "Teste@123"),
                        "Insira um e-mail válido"
                ),
                Arguments.of(
                        new LoginRequestDto("Teste", ""),
                        "Senha é obrigatório"
                )
        );
    }
}
