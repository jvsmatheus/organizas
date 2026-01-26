package com.organizas.organizas.controllers;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
import com.organizas.organizas.dto.response.CreateUserResponseDto;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ResponseBase<CreateUserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto dto, HttpServletRequest request) {
        return userService.createUser(dto, request);
    }
}
