package com.example.board.domain.board.service;

import com.example.board.domain.auth.entity.User;
import com.example.board.domain.board.presentation.dto.request.CreatePostRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyPostRequestDTO;
import com.example.board.domain.board.presentation.dto.response.PostPaginationResponseDTO;
import com.example.board.domain.board.presentation.dto.response.PostResponseDTO;

public interface PostService {

    PostResponseDTO getPostById(Long postId);

    PostPaginationResponseDTO getPostByPage(int page, int size);

    void createPost(CreatePostRequestDTO createPostRequestDTO, User user);

    void modifyPost(ModifyPostRequestDTO modifyPostRequestDTO, User user);

    void deletePost(Long postId, User user);
}
