package com.organizas.organizas.dto.errors;

public record FieldErrorResponse(
        String field,
        String message
) {
}
