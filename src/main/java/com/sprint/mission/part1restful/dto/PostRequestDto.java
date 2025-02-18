package com.sprint.mission.part1restful.dto;

import java.util.UUID;

public record PostRequestDto(
        UUID id,
        String title,
        String content
) {
}
