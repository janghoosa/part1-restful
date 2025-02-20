package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.dto.comment.CommentResponseDto;
import com.sprint.mission.part1restful.dto.comment.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.comment.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        CommentResponseDto comment = commentService.createComment(createCommentRequestDto);

        URI location = URI.create("/" + comment.getId());

        return ResponseEntity.created(location).body(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findByIdComment(@PathVariable Long id) {
        CommentResponseDto comment = commentService.findById(id);

        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDto>> findByPostIdComment(@PathVariable Long postId){
        List<CommentResponseDto> comments = commentService.findByPostId(postId);

        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        CommentResponseDto comment = commentService.updateComment(id, updateCommentRequestDto);

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}
