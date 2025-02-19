package com.sprint.mission.part1restful.dto;

import java.util.UUID;

public record PostRequestDto(
        Long id,
        String title,
        String content
) {
}
