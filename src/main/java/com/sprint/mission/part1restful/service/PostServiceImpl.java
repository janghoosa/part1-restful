package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Post;
import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostResponseDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import com.sprint.mission.part1restful.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public PostResponseDto create(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto);

        PostResponseDto postResponseDto = new PostResponseDto(
                postCreateDto.authorId(), postCreateDto.title(), postCreateDto.content());
        postRepository.save(post);
        return postResponseDto;
    }

    public PostResponseDto find(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        PostResponseDto postResponseDto = new PostResponseDto(post.getId(),post.getTitle(), post.getContent());
        return postResponseDto;
    }

    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(post -> new PostResponseDto(post.getId(),post.getTitle(), post.getContent()))
                .toList();

        return postResponseDtos;
    }

    public void update(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id).orElse(null);
        post.update(postUpdateDto);
        postRepository.save(post);

    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public PostPageResponse searchAllPaging(int pageNo, int pageSize, String sortBy) {
        // 페이지 번호, 페이지 크기 및 정렬 기준으로 Pageable 객체를 생성
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Post> postPage = postRepository.findAll( pageable);

        List<PostResponseDto> content = postPage.getContent().stream()
                .map(post-> new PostResponseDto(post.getId(), post.getTitle(), post.getContent())) // mapToDto 메서드를 이용해 변환
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
