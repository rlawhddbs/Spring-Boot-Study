package com.example.board.domain.board.service;

import com.example.board.common.error.CustomError;
import com.example.board.common.error.ErrorCode;
import com.example.board.domain.auth.entity.repository.UserRepository;
import com.example.board.domain.board.entity.Comment;
import com.example.board.domain.board.entity.Post;
import com.example.board.domain.board.entity.repository.CommentRepository;
import com.example.board.domain.board.entity.repository.PostRepository;
import com.example.board.domain.board.presentation.dto.request.CreateCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDTO> getCommentAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void createComment(CreateCommentRequestDTO createCommentRequestDTO) {

        Post post = postRepository.findById(createCommentRequestDTO.getPostId())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));

        commentRepository.save(Comment.builder()
                .user(userRepository.findById(1L).orElseThrow())
                .post(post)
                .content(createCommentRequestDTO.getContent())
                .build());
    }

    @Override
    public void modifyComment(ModifyCommentRequestDTO dto) {

        Comment comment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));

        comment.modifyComment(dto.getContent());

        commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);

    }

}
