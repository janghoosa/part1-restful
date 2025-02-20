package com.sprint.mission.part1restful.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostUpdateDto(
        @Schema(example =  "content")
        String content,

        @Schema(example = "Title")
        String title
) {
}
