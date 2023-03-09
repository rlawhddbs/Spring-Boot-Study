package com.example.board.domain.board.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "글 수정 요청 DTO")
public class ModifyPostRequestDTO {

    @NotBlank(message = "'postId'은(는) null이거나 공백일 수 없습니다")
    private Long postId;

    @NotBlank(message = "'title'은(는) null이거나 공백일 수 없습니다")
    private String title;

    @NotBlank(message = "'content'은(는) null이거나 공백일 수 없습니다")
    private String content;

}
