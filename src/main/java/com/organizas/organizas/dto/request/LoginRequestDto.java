package com.organizas.organizas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @NotBlank(message = "Senha é obrigatório")
        String password
) { }