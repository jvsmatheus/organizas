package com.organizas.organizas.utils;

import com.organizas.organizas.dto.response.ResponseBase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class BuildResponse {

    public <T> ResponseEntity<ResponseBase<T>> build(
            HttpStatus status,
            HttpServletRequest request,
            String message,
            T data
    ) {
        return ResponseEntity.status(status).body(
                new ResponseBase<T>(
                        Instant.now().truncatedTo(ChronoUnit.SECONDS),
                        status.value(),
                        request.getRequestURI(),
                        request.getMethod(),
                        message,
                        data
                )
        );
    }
}
