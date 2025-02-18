package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.domain.Comment;
import com.sprint.mission.part1restful.dto.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.CommentResponseDto;
import com.sprint.mission.part1restful.dto.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.service.CommentService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        CommentResponseDto comment = commentService.createComment(createCommentRequestDto);

        Map<String, Object> rep = new HashMap<>();
        rep.put("comment", comment);
        rep.put("status", HttpStatus.CREATED);
        rep.put("statusCode", 201);

        return new ResponseEntity<>(rep, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findByIdComment(@PathVariable Long id) {
        CommentResponseDto comment = commentService.findById(id);

        Map<String, Object> rep = new HashMap<>();
        rep.put("comment", comment);
        rep.put("status", HttpStatus.OK);
        rep.put("statusCode", 200);

        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> findByPostIdComment(@PathVariable Long id){
        List<CommentResponseDto> comments = commentService.findByPostId(id);

        Map<String, Object> rep = new HashMap<>();
        rep.put("comment", comments);
        rep.put("status", HttpStatus.OK);
        rep.put("statusCode", 200);

        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateComment(@PathVariable Long id,
                                                             @RequestBody UpdateCommentRequestDto updateCommentRequestDto){
        CommentResponseDto comment = commentService.updateComment(id, updateCommentRequestDto);

        Map<String, Object> rep = new HashMap<>();
        rep.put("comment", comment);
        rep.put("status", HttpStatus.OK);
        rep.put("statusCode", 200);

        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        Map<String, Object> rep = new HashMap<>();
        rep.put("status", HttpStatus.OK);
        rep.put("statusCode", 200);

        return new ResponseEntity<>(rep, HttpStatus.OK);
    }
}
