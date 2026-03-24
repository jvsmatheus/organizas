package com.organizas.organizas.dto.request;

import com.organizas.organizas.validation.StrongPassword.StrongPassword;

public record ResetPasswordRequestDto(
        @StrongPassword
        String newPassword
) {
}
