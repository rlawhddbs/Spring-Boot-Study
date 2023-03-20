package com.example.board.domain.board.service;

import com.example.board.domain.auth.entity.User;
import com.example.board.domain.auth.service.AuthService;
import com.example.board.domain.board.entity.Post;
import com.example.board.domain.board.entity.repository.PostRepository;
import com.example.board.domain.board.exception.PostNotFoundException;
import com.example.board.domain.board.presentation.dto.request.CreatePostRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyPostRequestDTO;
import com.example.board.domain.board.presentation.dto.response.PostPaginationResponseDTO;
import com.example.board.domain.board.presentation.dto.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService{

    private final AuthService authService;
    private final PostRepository postRepository;

    @Override
    public PostResponseDTO getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return new PostResponseDTO(post);
    }

    @Override
    public PostPaginationResponseDTO getPostByPage(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<PostResponseDTO> postList = postRepository.findAll(pageRequest).stream()
                .map(PostResponseDTO::new)
                .toList();

        return new PostPaginationResponseDTO(postList, page, size);
    }

    @Override
    @Transactional
    public void createPost(CreatePostRequestDTO dto, User user) {

        postRepository.save(Post.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build());
    }

    @Override
    @Transactional
    public void modifyPost(ModifyPostRequestDTO dto, User user) {

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        authService.checkUserPermission(user, post.getUser());

        post.modifyPost(dto.getTitle(), dto.getContent());

        postRepository.save(post);

    }

    @Override
    @Transactional
    public void deletePost(Long postId, User user) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        authService.checkUserPermission(user, post.getUser());

        postRepository.delete(post);

    }

}
