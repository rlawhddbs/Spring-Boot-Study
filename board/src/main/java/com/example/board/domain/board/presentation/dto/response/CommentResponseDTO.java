package com.example.board.domain.board.presentation.dto.response;

import com.example.board.domain.board.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Schema(description = "댓글 응답 DTO")
public class CommentResponseDTO {

    private Long commentId;

    private Long postId;

    private Long userId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
