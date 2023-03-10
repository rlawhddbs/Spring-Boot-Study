package com.example.board.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "'userId'은(는) null이거나 공백일 수 없습니다")
    private String userId;

    @NotBlank(message = "'password'은(는) null이거나 공백일 수 없습니다")
    private String password;

}
