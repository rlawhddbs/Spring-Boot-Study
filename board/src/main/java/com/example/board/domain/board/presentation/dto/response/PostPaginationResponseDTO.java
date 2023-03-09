package com.example.board.domain.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "글 페이지네이션 응답 DTO")
public class PostPaginationResponseDTO {

    private List<PostResponseDTO> posts;

    private int page;

    private int size;

}
