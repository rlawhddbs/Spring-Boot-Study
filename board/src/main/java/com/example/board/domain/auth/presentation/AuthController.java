package com.example.board.domain.auth.presentation;

import com.example.board.common.response.DataResponse;
import com.example.board.common.response.Response;
import com.example.board.domain.auth.presentation.dto.response.LoginResponseDTO;
import com.example.board.domain.auth.presentation.dto.request.LoginRequestDTO;
import com.example.board.domain.auth.presentation.dto.request.RegisterRequestDTO;
import com.example.board.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "인증 API Document")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입 요청")
    @PostMapping("/register")
    public ResponseEntity<Response> register(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO
    ) {

        authService.register(registerRequestDTO);

        return Response.created("회원가입 성공");
    }

    @Operation(summary = "로그인 요청 및 토큰 반환")
    @PostMapping("/login")
    public ResponseEntity<DataResponse<LoginResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO
    ) {

        LoginResponseDTO token = authService.login(loginRequestDTO);

        return DataResponse.ok("로그인 성공", token);
    }

}
