package com.organizas.organizas.dto.request;

import com.organizas.organizas.validation.StrongPassword.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @StrongPassword
        String password
) { }