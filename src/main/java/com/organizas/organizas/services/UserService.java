package com.organizas.organizas.services;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
import com.organizas.organizas.dto.response.CreateUserResponseDto;
import com.organizas.organizas.entities.User;
import com.organizas.organizas.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto request) {
        var newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());

        var savedUser = userRepository.save(newUser);

        return new CreateUserResponseDto(savedUser.getName(), savedUser.getEmail());
    }
}
