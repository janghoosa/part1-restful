package com.sprint.mission.part1restful.dto;

import com.sprint.mission.part1restful.domain.Comment;
import lombok.Getter;

import java.time.Instant;

@Getter
public class CommentResponseDto {
    Long id;
    Instant createAt;
    Instant updateAt;
    Long userId;
    Long postId;
    String content;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.createAt = comment.getCreateAt();
        this.updateAt = comment.getUpdateAt();
        this.userId = comment.getUserId();
        this.postId = comment.getPostId();
        this.content = comment.getContent();
    }
}
