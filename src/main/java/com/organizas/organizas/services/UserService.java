package com.organizas.organizas.services;

import com.organizas.organizas.dto.request.CreateUserRequestDto;
import com.organizas.organizas.dto.response.CreateUserResponseDto;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.entities.User;
import com.organizas.organizas.repositories.UserRepository;
import com.organizas.organizas.utils.BuildResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuildResponse buildResponse;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BuildResponse buildResponse) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.buildResponse = buildResponse;
    }

    public ResponseEntity<ResponseBase<CreateUserResponseDto>> createUser(CreateUserRequestDto dto, HttpServletRequest request) {
        var newUser = new User();
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setEmail(dto.email());
        newUser.setName(dto.name());

        var savedUser = userRepository.save(newUser);

        return buildResponse.build(
                HttpStatus.CREATED,
                request,
                "Usuário criado com sucesso!",
                new CreateUserResponseDto(savedUser.getName(), savedUser.getEmail())
        );
    }
}
