package com.sprint.mission.part1restful.dto;

import lombok.Builder;

@Builder
public record WeatherRequestDto(String location) {

}
