package com.sprint.mission.part1restful.service.comment;

import com.sprint.mission.part1restful.dto.comment.CommentResponseDto;
import com.sprint.mission.part1restful.dto.comment.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.comment.UpdateCommentRequestDto;

import java.util.List;

public interface CommentService {
    // 댓글 작성
    CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto);

    // 댓글 조회
    CommentResponseDto findById(Long id);

    List<CommentResponseDto> findByPostId(Long postId);

    // 댓글 수정
    CommentResponseDto updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto);

    // 댓글 삭제
    void deleteComment(Long id);
}
