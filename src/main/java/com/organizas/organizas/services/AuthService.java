package com.organizas.organizas.services;

import com.organizas.organizas.dto.request.LoginRequestDto;
import com.organizas.organizas.dto.response.LoginResponseDto;
import com.organizas.organizas.security.UserDetailsImpl;
import com.organizas.organizas.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponseDto login(LoginRequestDto request) {
        var userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(request.email());

        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var token = jwtUtils.generateToken(userDetails);
        return new LoginResponseDto(token);
    }
}
