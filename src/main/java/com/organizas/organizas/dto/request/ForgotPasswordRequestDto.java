package com.organizas.organizas.dto.request;

import jakarta.validation.constraints.Email;

public record ForgotPasswordRequestDto(
        @Email String email
) {
}
