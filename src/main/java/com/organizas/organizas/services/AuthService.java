package com.organizas.organizas.services;

import com.organizas.organizas.dto.request.ForgotPasswordRequestDto;
import com.organizas.organizas.dto.request.LoginRequestDto;
import com.organizas.organizas.dto.response.LoginResponseDto;
import com.organizas.organizas.dto.response.ResponseBase;
import com.organizas.organizas.entities.User;
import com.organizas.organizas.entities.VerificationToken;
import com.organizas.organizas.exceptions.exceptions.UserNotFoundException;
import com.organizas.organizas.repositories.UserRepository;
import com.organizas.organizas.repositories.VerificationTokenRepository;
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final BuildResponse buildResponse;

    public AuthService(UserDetailsService userDetailsService, UserService userService, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, EmailService emailService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, BuildResponse buildResponse) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.buildResponse = buildResponse;
    }

    public ResponseEntity<ResponseBase<LoginResponseDto>> login(LoginRequestDto dto, HttpServletRequest request) {
        var userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(dto.email());

        if (!passwordEncoder.matches(dto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException(null);
        }

        var token = jwtUtils.generateToken(userDetails.getId().toString());

        return buildResponse.build(
                HttpStatus.OK,
                request,
                null,
                new LoginResponseDto(token)
        );
    }

    public ResponseEntity<ResponseBase<Void>> confirmEmail(String token, HttpServletRequest request) {
        Optional<UUID> userId = jwtUtils.validateToken(token);
        User user = userId
                .flatMap(userRepository::findById)
                        .orElseThrow(() -> new UserNotFoundException(null));

        user.setEmailVerified(true);
        userRepository.save(user);

        return buildResponse.build(
                HttpStatus.OK,
                request,
                null,
                null
        );
    }

    public ResponseEntity<ResponseBase<Void>> forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto, HttpServletRequest request) {
        Optional<User> user = userRepository.findByEmail(forgotPasswordRequestDto.email());

        if (user.isPresent()) {
            String token = jwtUtils.generateToken(user.get().getId().toString());

            var verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUser(user.get());
            verificationToken.setExpiresAt(Date.from(Instant.now().plusMillis(900000)));

            verificationTokenRepository.save(verificationToken);
            emailService.sendForgotPasswordEmail(verificationToken.getToken(), verificationToken.getUser().getEmail());
        }

        return buildResponse.build(
                HttpStatus.OK,
                request,
                "Se o email existir, enviaremos instruções.",
                null
        );
    }
}
