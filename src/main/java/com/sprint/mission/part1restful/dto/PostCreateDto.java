package com.sprint.mission.part1restful.dto;

import java.util.UUID;

public record PostCreateDto(
        Long authorId,
        String title,
        String content
) {
}
