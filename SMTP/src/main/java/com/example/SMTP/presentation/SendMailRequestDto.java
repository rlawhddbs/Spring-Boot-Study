package com.example.SMTP.presentation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SendMailRequestDto {

    @NotNull
    private String address;

    @NotNull
    private String title;

    @NotNull
    private String content;

}
