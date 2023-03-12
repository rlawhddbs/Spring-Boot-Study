package com.example.board.domain.auth.service;

import com.example.board.domain.auth.presentation.dto.response.LoginResponseDTO;
import com.example.board.domain.auth.presentation.dto.request.LoginRequestDTO;
import com.example.board.domain.auth.presentation.dto.request.RegisterRequestDTO;

public interface AuthService {

    void register(RegisterRequestDTO registerRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
