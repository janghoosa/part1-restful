package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostRequestDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import com.sprint.mission.part1restful.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public String createPost(@RequestBody PostCreateDto postCreateDto) {
        postService.create(postCreateDto);
        return "Post created successfully";
    }

    @GetMapping("/{id}")
    public PostRequestDto getPost(@PathVariable Long id) {
        return postService.find(id);

    }

    @GetMapping
    public PostPageResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return postService.searchAllPaging(pageNo, pageSize, sortBy);
    }


    @PutMapping("/{id}")
    public String updatePost(@PathVariable("") Long id, @RequestBody PostUpdateDto postUpdateDto) {
        postService.update(id, postUpdateDto);
        return "Post updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "Post deleted successfully";
    }
}
