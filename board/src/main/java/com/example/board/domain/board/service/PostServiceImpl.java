package com.example.board.domain.board.service;

import com.example.board.common.error.CustomError;
import com.example.board.common.error.ErrorCode;
import com.example.board.domain.auth.entity.repository.UserRepository;
import com.example.board.domain.board.entity.Post;
import com.example.board.domain.board.entity.repository.CommentRepository;
import com.example.board.domain.board.entity.repository.PostRepository;
import com.example.board.domain.board.presentation.dto.request.CreatePostRequestDTO;
import com.example.board.domain.board.presentation.dto.request.ModifyPostRequestDTO;
import com.example.board.domain.board.presentation.dto.response.PostPaginationResponseDTO;
import com.example.board.domain.board.presentation.dto.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public PostResponseDTO getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));

        return new PostResponseDTO(post);
    }

    @Override
    public PostPaginationResponseDTO getPostByPage(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<PostResponseDTO> postList = postRepository.findAll(pageRequest).stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        return new PostPaginationResponseDTO(postList, page, size);
    }

    @Override
    public void createPost(CreatePostRequestDTO dto) {

        postRepository.save(Post.builder()
                .user(userRepository.findById(1L).orElseThrow())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build());
    }

    @Override
    public void modifyPost(ModifyPostRequestDTO dto) {

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));

        post.modifyPost(dto.getTitle(), dto.getContent());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {

        postRepository.deleteById(postId);

    }

}
