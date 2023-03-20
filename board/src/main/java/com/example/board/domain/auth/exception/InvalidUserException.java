package com.example.board.domain.auth.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.CustomException;

public class InvalidUserException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidUserException();

    private InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }
}
