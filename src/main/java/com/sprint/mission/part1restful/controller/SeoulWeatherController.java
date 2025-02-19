package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.service.SeoulWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/seoul")
public class SeoulWeatherController {
    private final SeoulWeatherService seoulWeatherService;

    public SeoulWeatherController(SeoulWeatherService seoulWeatherService) {
        this.seoulWeatherService = seoulWeatherService;
    }

    @GetMapping
    public String getWeather() throws IOException {
        return seoulWeatherService.getWeatherData();
    }
}
