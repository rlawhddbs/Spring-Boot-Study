package com.example.SMTP.presentation;

import com.example.SMTP.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping
    public void sendMail(@RequestBody SendMailRequestDto sendMailRequestDto) {

        mailService.sendMail(sendMailRequestDto);

    }

}
