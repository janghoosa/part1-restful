package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Post;
import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostRequestDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import com.sprint.mission.part1restful.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        PostRequestDto postRequestDto = new PostRequestDto(post.getId(),post.getTitle(), post.getContent());
        return postRequestDto;
    }

    public List<PostRequestDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostRequestDto> postRequestDtos = posts.stream()
                .map(post -> new PostRequestDto(post.getId(),post.getTitle(), post.getContent()))
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

    public PostPageResponse searchAllPaging(int pageNo, int pageSize, String sortBy) {
        // 페이지 번호, 페이지 크기 및 정렬 기준으로 Pageable 객체를 생성
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Post> postPage = postRepository.findAll( pageable);

        List<PostRequestDto> content = postPage.getContent().stream()
                .map(post-> new PostRequestDto(post.getId(), post.getTitle(), post.getContent())) // mapToDto 메서드를 이용해 변환
                .toList();

        // PageResponse 객체 반환
        return PostPageResponse.builder()
                .content(content) // 변환된  리스트
                .pageNo(pageNo) // 현재 페이지 번호
                .pageSize(pageSize) // 페이지 크기
                .totalElements(postPage.getTotalElements()) // 전체 요소 개수
                .totalPages(postPage.getTotalPages()) // 전체 페이지 개수
                .last(postPage.isLast()) // 마지막 페이지 여부
                .build();
    }

}
