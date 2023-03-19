package com.example.board.common.interceptor;

import com.example.board.common.annotation.CheckAuthorization;
import com.example.board.common.exception.CustomException;
import com.example.board.common.error.ErrorCode;
import com.example.board.common.extractor.AuthExtractor;
import com.example.board.common.jwt.JwtUtil;
import com.example.board.domain.auth.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final AuthExtractor authExtractor;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        CheckAuthorization checkAuthorization = handlerMethod.getMethodAnnotation(CheckAuthorization.class);

        if (checkAuthorization == null) {
            return true;
        }

        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        User user = jwtUtil.verifyToken(token);
        if (user == null) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        request.setAttribute("user", user);
        return true;
    }

}
