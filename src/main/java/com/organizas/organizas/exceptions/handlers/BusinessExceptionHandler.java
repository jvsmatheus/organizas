package com.organizas.organizas.exceptions.handlers;

import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.exceptions.exceptions.EmailAlreadyExistsException;
import com.organizas.organizas.utils.BuildResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(3)
@RestControllerAdvice
public class BusinessExceptionHandler {
    private final BuildResponse buildResponse;

    public BusinessExceptionHandler(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseBase<Void>> handleEmailAlreadyExists(
            HttpServletRequest request
    ) {
        return buildResponse.build(
                HttpStatus.CONFLICT,
                request,
                "E-mail já cadastrado.",
                null
        );
    }
}
