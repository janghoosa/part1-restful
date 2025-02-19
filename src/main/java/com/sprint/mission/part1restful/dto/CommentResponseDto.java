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

    public CommentResponseDto(Long id, Instant createAt, Instant updateAt, Long userId, Long postId, String content) {
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public static CommentResponseDto fromEntity(Comment comment) {
        return new CommentResponseDto(
                comment.getId()
                , comment.getCreateAt()
                , comment.getUpdateAt()
                , comment.getUserId()
                , comment.getPostId()
                , comment.getContent()
        );
    }
}
