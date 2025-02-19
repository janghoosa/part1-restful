package com.sprint.mission.part1restful.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record WeatherResponseDto(
        String location,
        String temperature,
        String condition,
        Instant recordedAt
) {
}
