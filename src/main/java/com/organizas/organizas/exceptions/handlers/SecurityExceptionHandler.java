package com.organizas.organizas.exceptions.handlers;

import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.utils.BuildResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class SecurityExceptionHandler {
    private final BuildResponse buildResponse;

    public SecurityExceptionHandler(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseBase<Void>> handleBadCredentialsUsernameNotFound(
            HttpServletRequest request
    ) {
        return buildResponse.build(
                HttpStatus.UNAUTHORIZED,
                request,
                "E-mail e/ou senha inválidos.",
                null
        );
    }
}
