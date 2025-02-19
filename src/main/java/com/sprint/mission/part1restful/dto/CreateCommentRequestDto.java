package com.sprint.mission.part1restful.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto {
    Long userId;
    Long postId;
    String content;
}
