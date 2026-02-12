package com.organizas.organizas.dto.email;

public record EmailDetails(
        String to,
        String subject,
        String body
) {
}
