package com.example.board.domain.board.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.exception.CustomException;

public class CommentNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }

}
