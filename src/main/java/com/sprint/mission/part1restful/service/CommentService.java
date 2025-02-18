package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.dto.CommentResponseDto;
import com.sprint.mission.part1restful.dto.CreateCommentRequestDto;
import com.sprint.mission.part1restful.dto.UpdateCommentRequestDto;

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
