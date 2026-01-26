package com.organizas.organizas.dto.response;

import java.time.Instant;

public record ResponseBase<T>(
        Instant timestamp,
        int status,
        String path,
        String method,
        String message,
        T data
) {
}
