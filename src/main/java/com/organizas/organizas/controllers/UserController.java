package com.organizas.organizas.controllers;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
import com.organizas.organizas.dto.response.CreateUserResponseDto;
import com.organizas.organizas.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto request) {
        var user = userService.createUser(request);
        URI location = URI.create("user/" + user.email());

        return ResponseEntity.created(location).body(new CreateUserResponseDto(
                user.name(), user.email()
        ));
    }
}
