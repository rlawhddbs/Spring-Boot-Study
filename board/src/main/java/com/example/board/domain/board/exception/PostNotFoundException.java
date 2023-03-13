package com.example.board.domain.board.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.CustomException;

public class PostNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new PostNotFoundException();

    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }

}
