package com.organizas.organizas.exceptions;

import com.organizas.organizas.dto.errors.FieldErrorResponse;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.utils.BuildResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BuildResponse buildResponse;

    public GlobalExceptionHandler(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
    }

    // Erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseBase<List<FieldErrorResponse>>> handleValidatioException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldErrorResponse> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorResponse(
                        err.getField(),
                        err.getDefaultMessage()
                )).toList();

        return buildResponse.build(
                HttpStatus.BAD_REQUEST,
                request,
                "Erro de validação.",
                details
        );
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
