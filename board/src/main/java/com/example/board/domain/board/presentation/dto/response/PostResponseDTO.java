package com.example.board.domain.board.presentation.dto.response;

import com.example.board.domain.board.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Schema(description = "글 응답 DTO")
public class PostResponseDTO {

    private Long userId;

    private String userName;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostResponseDTO(Post post) {
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUserName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
