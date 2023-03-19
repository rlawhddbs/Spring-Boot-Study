package com.example.board.domain.auth.service;

import com.example.board.common.exception.CustomException;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.jwt.JwtUtil;
import com.example.board.domain.auth.entity.User;
import com.example.board.domain.auth.entity.repository.UserRepository;
import com.example.board.domain.auth.presentation.dto.response.LoginResponseDTO;
import com.example.board.domain.auth.presentation.dto.request.LoginRequestDTO;
import com.example.board.domain.auth.presentation.dto.request.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(RegisterRequestDTO registerRequestDTO) {

        userRepository.save(User.builder()
                .userName(registerRequestDTO.getUserName())
                .password(registerRequestDTO.getPassword())
                .build());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository.findByUserName(loginRequestDTO.getUserName())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!(user.getPassword().equals(loginRequestDTO.getPassword()))) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        return LoginResponseDTO.builder()
                .accessToken(jwtUtil.generateAccessToken(user.getUserName()))
                .refreshToken(jwtUtil.generateRefreshToken(user.getUserName()))
                .build();
    }

}
