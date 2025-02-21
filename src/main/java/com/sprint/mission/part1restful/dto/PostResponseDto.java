package com.sprint.mission.part1restful.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostResponseDto(
        @Schema(example= "1")
        Long id,

        @Schema(example = "Post Title 1")
        String title,

        @Schema(example =  "Content")
        String content
) {
}
