package com.example.board.domain.board.presentation;

import com.example.board.common.annotation.CheckAuthorization;
import com.example.board.common.response.DataResponse;
import com.example.board.common.response.Response;
import com.example.board.domain.auth.entity.User;
import com.example.board.domain.board.presentation.dto.request.CreatePostRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyPostRequestDTO;
import com.example.board.domain.board.presentation.dto.response.PostPaginationResponseDTO;
import com.example.board.domain.board.presentation.dto.response.PostResponseDTO;
import com.example.board.domain.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Post", description = "게시물 API Document")
public class PostController {

    private final PostService postService;

    @Operation(summary = "글 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<DataResponse<PostResponseDTO>> getPostById(
            @PathVariable Long postId
    ) {

        PostResponseDTO post = postService.getPostById(postId);

        return DataResponse.ok("게시물 조회 성공", post);

    }

    @Operation(summary = "글 목록 조회 (페이지네이션)")
    @GetMapping
    public ResponseEntity<DataResponse<PostPaginationResponseDTO>> getPostByPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        PostPaginationResponseDTO postPagination = postService.getPostByPage(page, size);

        return DataResponse.ok("게시물 페이지네이션 조회 성공", postPagination);
    }

    @CheckAuthorization
    @Operation(summary = "글 작성")
    @PostMapping
    public ResponseEntity<Response> createPost(
            @Valid @RequestBody CreatePostRequestDTO createPostRequestDTO,
            @RequestAttribute User user
    ) {

        postService.createPost(createPostRequestDTO);

        return Response.created("게시물 작성 성공");
    }

    @Operation(summary = "글 수정")
    @PutMapping
    public ResponseEntity<Response> modifyPost(
            @Valid @RequestBody ModifyPostRequestDTO modifyPostRequestDTO
    ) {

        postService.modifyPost(modifyPostRequestDTO);

        return Response.ok("게시물 수정 성공");
    }

    @Operation(summary = "글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Response> deletePost(
            @PathVariable Long postId
    ) {

        postService.deletePost(postId);

        return Response.ok("게시물 삭제 성공");
    }

}
