package com.sprint.mission.part1restful.controller;

import com.sprint.mission.part1restful.service.SeoulApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/seoul")
public class SeoulApiController {
    private final SeoulApiService seoulApiService;

    public SeoulApiController(SeoulApiService seoulApiService) {
        this.seoulApiService = seoulApiService;
    }

    @GetMapping("/citydata")
    public String getCityData() throws IOException {
        return seoulApiService.getCityData(); // API 호출
    }
}
