package com.organizas.organizas.exceptions.handlers;

import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.utils.BuildResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(99)
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BuildResponse buildResponse;

    public GlobalExceptionHandler(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
    }

    // Erro genérico
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseBase<Void>> handleGenericException(Exception ex, HttpServletRequest request) {
        return buildResponse.build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                request,
                "Erro interno do servidor, por favor contate o suporte.",
                null
        );
    }
}
