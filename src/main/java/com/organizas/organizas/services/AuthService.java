package com.organizas.organizas.services;

import com.organizas.organizas.dto.request.LoginRequestDto;
import com.organizas.organizas.dto.response.LoginResponseDto;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.security.UserDetailsImpl;
import com.organizas.organizas.utils.BuildResponse;
import com.organizas.organizas.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final BuildResponse buildResponse;

    public AuthService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, BuildResponse buildResponse) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.buildResponse = buildResponse;
    }

    public ResponseEntity<ResponseBase<LoginResponseDto>> login(LoginRequestDto dto, HttpServletRequest request) {
        var userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(dto.email());

        if (!passwordEncoder.matches(dto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var token = jwtUtils.generateToken(userDetails);

        return buildResponse.build(
                HttpStatus.OK,
                request,
                null,
                new LoginResponseDto(token)
        );
    }
}
