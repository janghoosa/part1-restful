package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Post;
import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostRequestDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import com.sprint.mission.part1restful.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto);
        postRepository.save(post);
    }

    public PostRequestDto find(UUID id) {
        Post post = postRepository.findById(id).orElse(null);
        PostRequestDto postRequestDto = new PostRequestDto(post.getTitle(), post.getContent());
        return postRequestDto;
    }

    public List<PostRequestDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostRequestDto> postRequestDtos = posts.stream()
                .map(post -> new PostRequestDto(post.getTitle(), post.getContent()))
                .toList();

        return postRequestDtos;
    }

    public void update(UUID id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id).orElse(null);
        post.update(postUpdateDto);
        postRepository.save(post);

    }

    public void delete(UUID id) {
        postRepository.deleteById(id);
    }
}
