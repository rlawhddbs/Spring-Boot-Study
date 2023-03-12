package com.example.board.domain.board.presentation;

import com.example.board.common.response.DataResponse;
import com.example.board.common.response.Response;
import com.example.board.domain.board.presentation.dto.request.CreateCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyCommentRequestDTO;
import com.example.board.domain.board.presentation.dto.response.CommentResponseDTO;
import com.example.board.domain.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "Comment", description = "댓글 API Document")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회")
    @GetMapping
    public ResponseEntity<DataResponse<List<CommentResponseDTO>>> getCommentAll() {

        List<CommentResponseDTO> comment = commentService.getCommentAll();

        return DataResponse.ok("댓글 목록 조회 성공", comment);
    }

    @Operation(summary = "댓글 작성")
    @PostMapping
    public ResponseEntity<Response> createComment(
            @Valid @RequestBody CreateCommentRequestDTO createCommentRequestDTO
    ) {

        commentService.createComment(createCommentRequestDTO);

        return Response.created("댓글 작성 성공");
    }

    @Operation(summary = "댓글 수정")
    @PutMapping
    public ResponseEntity<Response> modifyComment(
            @Valid @RequestBody ModifyCommentRequestDTO modifyCommentRequestDTO
    ) {

        commentService.modifyComment(modifyCommentRequestDTO);

        return Response.ok("댓글 수정 성공");
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Response> deleteComment(
            @PathVariable Long commentId
    ) {

        commentService.deleteComment(commentId);

        return Response.ok("댓글 삭제 성공");
    }

}
