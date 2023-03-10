package com.example.board.domain.auth.presentation.dto.response;

import java.time.LocalDateTime;

public class LoginResponseDTO {

    private String accessToken;

    private LocalDateTime accessTokenExpiredIn;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiredIn;

}
