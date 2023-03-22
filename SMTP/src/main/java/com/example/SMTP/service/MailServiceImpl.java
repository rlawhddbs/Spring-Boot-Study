package com.example.SMTP.service;

import com.example.SMTP.presentation.SendMailRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String SEND_FROM_ADDRESS;

    @Override
    public void sendMail(SendMailRequestDto sendMailRequestDto) {

        log.info(SEND_FROM_ADDRESS);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(SEND_FROM_ADDRESS);
        message.setTo(sendMailRequestDto.getAddress());
        message.setSubject(sendMailRequestDto.getTitle());
        message.setText(sendMailRequestDto.getContent());

        javaMailSender.send(message);

    }

}
