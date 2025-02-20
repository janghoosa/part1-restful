package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{msrstename}")
    public Double getPm25ByRegion(@PathVariable String msrstename) {
        return weatherService.getPm25ByRegion(msrstename);
    }
}
