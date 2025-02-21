package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.domain.Comment;
import com.sprint.mission.part1restful.dto.comment.CommentResponseDto;
import com.sprint.mission.part1restful.dto.comment.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.comment.UpdateCommentRequestDto;
import com.sprint.mission.part1restful.service.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "댓글 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @ApiResponse(
            responseCode = "201",
            description = "댓글 생성 성공",
            content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = Comment.class))
    )
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        CommentResponseDto comment = commentService.createComment(createCommentRequestDto);

        URI location = URI.create("/" + comment.getId());

        return ResponseEntity.created(location).body(comment);
    }

    @Operation(summary = "댓글 단건 조회", description = "특정 ID의 댓글을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "댓글 단건 조회 성공",
            content = @Content(mediaType = "application/json"
            ,schema = @Schema(implementation = Comment.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "댓글을 찾을 수 없음"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findByIdComment(@PathVariable Long id) {
        CommentResponseDto comment = commentService.findById(id);

        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "댓글 다건 조회", description = "특정 게시글의 댓글을 모두 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "댓글 다건 조회 성공",
            content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = Comment.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음"
    )
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDto>> findByPostIdComment(@PathVariable Long postId){
        List<CommentResponseDto> comments = commentService.findByPostId(postId);

        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "댓글 수정", description = "특정 ID의 댓글을 수정합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "댓글 수정 성공",
            content = @Content(mediaType = "application/json"
                    ,schema = @Schema(implementation = Comment.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "댓글을 찾을 수 없음"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        CommentResponseDto comment = commentService.updateComment(id, updateCommentRequestDto);

        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "댓글 단건 조회", description = "특정 ID의 댓글을 조회합니다.")
    @ApiResponse(
            responseCode = "204",
            description = "댓글 삭제 성공"
    )
    @ApiResponse(
            responseCode = "404",
            description = "댓글을 찾을 수 없음"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}
