package com.organizas.organizas.services;

import com.organizas.organizas.dto.email.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String sender;
    private final String baseUrl;

    public EmailService(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}") String sender,
            @Value("${spring.application.base_url}") String baseUrl
    ) {
        this.javaMailSender = javaMailSender;
        this.sender = sender;
        this.baseUrl = baseUrl;
    }

    public void sendConfirmationEmail(String token, String userEmail) {
        var confirmationUrl = baseUrl.concat("/auth/verify-email?token=").concat(token);

        sendMail(new EmailDetails(
                        userEmail,
                        "Confirmação de email - Organizas",
                        "Clique no link para ativar sua conta: " + confirmationUrl
                )
        );
    }

    public void sendForgotPasswordEmail(String token, String userEmail) {
        var confirmationUrl = baseUrl.concat("/auth/reset-password?token=").concat(token);

        sendMail(new EmailDetails(
                        userEmail,
                        "Redefinição de senha - Organizas",
                        "Clique no link para redefinir sua senha: " + confirmationUrl
                )
        );
    }

    private void sendMail(EmailDetails emailDetails) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(emailDetails.to());
        message.setSubject(emailDetails.subject());
        message.setText(emailDetails.body());

        javaMailSender.send(message);
    }
}
