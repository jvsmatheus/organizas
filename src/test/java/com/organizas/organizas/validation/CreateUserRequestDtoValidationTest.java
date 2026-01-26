package com.organizas.organizas.validation;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
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

public class CreateUserRequestDtoValidationTest extends ValidationTestBase {

    @Autowired
    private Validator validator;

    @ParameterizedTest
    @MethodSource("invalidCreateUserRequests")
    void shouldFailValidation(CreateUserRequestDto dto, String expectedMessage) {
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());

        var messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertTrue(messages.contains(expectedMessage));
    }

    @Test
    void shouldPassWhenBodyIsValid() {
        var dto = new CreateUserRequestDto("Teste", "teste@email.com", "Teste@123");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    static Stream<Arguments> invalidCreateUserRequests() {
        return Stream.of(
                Arguments.of(
                        new CreateUserRequestDto("", "teste@email.com", "Teste@123"),
                        "Nome é obrigatório"
                ),
                Arguments.of(
                        new CreateUserRequestDto("Teste", "", "Teste@123"),
                        "E-mail é obrigatório"
                ),
                Arguments.of(
                        new CreateUserRequestDto("Teste", "teste", "Teste@123"),
                        "Insira um e-mail válido"
                ),
                Arguments.of(
                        new CreateUserRequestDto("Teste", "teste@email.com", "teste"),
                        "A senha deve conter no mínimo 8 caracteres, 1 letra maiúscula, 1 número e 1 caractere especial"
                ),
                Arguments.of(
                        new CreateUserRequestDto("Teste", "teste@email.com", "teste123"),
                        "A senha deve conter no mínimo 8 caracteres, 1 letra maiúscula, 1 número e 1 caractere especial"
                )
        );
    }
}