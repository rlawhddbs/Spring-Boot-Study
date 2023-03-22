package com.example.SMTP.service;

import com.example.SMTP.presentation.SendMailRequestDto;

public interface MailService {

    void sendMail(SendMailRequestDto sendMailRequestDto);

}
