package com.example.board.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequestDTO {

    @NotBlank(message = "'userName'은(는) null이거나 공백일 수 없습니다")
    private String userName;

    @NotBlank(message = "'password'은(는) null이거나 공백일 수 없습니다")
    @Size(min = 128, max = 128, message = "패스워드가 SHA-512으로 암호화 되어있지 않습니다")
    private String password;

}
