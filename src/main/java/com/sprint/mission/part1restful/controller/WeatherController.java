package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.dto.WeatherRequestDto;
import com.sprint.mission.part1restful.dto.WeatherResponseDto;
import com.sprint.mission.part1restful.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{location}")
    public WeatherResponseDto getWeather(@RequestParam String location) {
        WeatherRequestDto request = WeatherRequestDto.builder().location(location).build();
        return weatherService.getWeather(request);
    }
}
