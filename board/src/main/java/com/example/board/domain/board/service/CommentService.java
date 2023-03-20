package com.example.board.domain.board.service;

import com.example.board.domain.auth.entity.User;
import com.example.board.domain.board.presentation.dto.request.CreateCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    List<CommentResponseDTO> getCommentAll();

    void createComment(CreateCommentRequestDTO createCommentRequestDTO, User user);

    void modifyComment(ModifyCommentRequestDTO modifyCommentRequestDTO, User user);

    void deleteComment(Long commentId, User user);

}
