package com.example.board.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),

    INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰"),
    TOKEN_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "토큰이 입력되지 않았습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다"),

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
