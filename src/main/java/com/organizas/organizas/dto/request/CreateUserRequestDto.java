package com.organizas.organizas.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequestDto(
        @NotEmpty(message = "Nome é obrigatório") String name,
        @NotEmpty(message = "Email é obrigatório") String email,
        @NotEmpty(message = "Senha é obrigatório") String password
) { }