package com.organizas.organizas.controllers;

import com.organizas.organizas.dto.request.LoginRequestDto;
import com.organizas.organizas.dto.response.LoginResponseDto;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBase<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {
        return authService.login(dto, request);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<ResponseBase<Void>> confirmEmail(@RequestParam String token, HttpServletRequest request) {
        return authService.confirmEmail(token, request);
    }
}
