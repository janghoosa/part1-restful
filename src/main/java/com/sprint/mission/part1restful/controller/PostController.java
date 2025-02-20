package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.apidocs.PostApiDocs;
import com.sprint.mission.part1restful.dto.PostCreateDto;
import com.sprint.mission.part1restful.dto.PostPageResponse;
import com.sprint.mission.part1restful.dto.PostResponseDto;
import com.sprint.mission.part1restful.dto.PostUpdateDto;
import com.sprint.mission.part1restful.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController implements PostApiDocs {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateDto postCreateDto) {
        PostResponseDto postResponseDto = postService.create(postCreateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.find(id);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping
    public ResponseEntity<PostPageResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        PostPageResponse postPageResponse = postService.searchAllPaging(pageNo, pageSize, sortBy);
        return ResponseEntity.ok(postPageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
        postService.update(id, postUpdateDto);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
