package com.fgear.webapp.service.emailService;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String email,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fgearhcmc@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
    }
}
