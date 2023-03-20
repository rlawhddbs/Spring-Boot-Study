package com.example.board.domain.board.service;

import com.example.board.domain.auth.entity.User;
import com.example.board.domain.auth.service.AuthService;
import com.example.board.domain.board.entity.Comment;
import com.example.board.domain.board.entity.Post;
import com.example.board.domain.board.entity.repository.CommentRepository;
import com.example.board.domain.board.entity.repository.PostRepository;
import com.example.board.domain.board.exception.CommentNotFoundException;
import com.example.board.domain.board.exception.PostNotFoundException;
import com.example.board.domain.board.presentation.dto.request.CreateCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final AuthService authService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDTO> getCommentAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public void createComment(CreateCommentRequestDTO createCommentRequestDTO, User user) {

        Post post = postRepository.findById(createCommentRequestDTO.getPostId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        commentRepository.save(Comment.builder()
                .user(user)
                .post(post)
                .content(createCommentRequestDTO.getContent())
                .build());

    }

    @Override
    @Transactional
    public void modifyComment(ModifyCommentRequestDTO dto, User user) {

        Comment comment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        authService.checkUserPermission(user, comment.getUser());

        comment.modifyComment(dto.getContent());

        commentRepository.save(comment);

    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        authService.checkUserPermission(user, comment.getUser());

        commentRepository.delete(comment);

    }

}
