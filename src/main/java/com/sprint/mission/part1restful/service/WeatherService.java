package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.domain.Weather;
import com.sprint.mission.part1restful.dto.WeatherRequestDto;
import com.sprint.mission.part1restful.dto.WeatherResponseDto;
import com.sprint.mission.part1restful.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private static final Duration CACHE_DURATION = Duration.ofMinutes(30);

    public WeatherResponseDto getWeather(WeatherRequestDto request) {
        String location = request.location();

        // DB에서 30분 내 데이터가 있으면 캐시 사용
        Optional<Weather> recentWeather = weatherRepository.findFirstByLocationOrderByRecordedAtDesc(location);
        if (recentWeather.isPresent() && isRecent(recentWeather.get().getRecordedAt())) {
            Weather weather = recentWeather.get();
            return WeatherResponseDto.builder()
                    .location(weather.getLocation())
                    .temperature(weather.getTemperature())
                    .condition(weather.getCondition())
                    .recordedAt(weather.getRecordedAt())
                    .build();
        }

        // 외부 API 호출
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("location", location)
                .queryParam("apiKey", apiKey) // API 키 추가
                .toUriString();

        try {
            WeatherResponseDto response = restTemplate.getForObject(url, WeatherResponseDto.class);

            if (response == null) {
                throw new IllegalStateException("외부 API 응답이 null입니다.");
            }

            // 가져온 데이터 저장
            Weather weather = Weather.builder()
                    .location(response.location())
                    .temperature(response.temperature())
                    .condition(response.condition())
                    .recordedAt(Instant.now())
                    .build();
            weatherRepository.save(weather);

            return response;
        } catch (RestClientException e) {
            throw new IllegalStateException("외부 API 호출 중 오류 발생: " + e.getMessage());
        }
    }

    private boolean isRecent(Instant recordedAt) {
        return Duration.between(recordedAt, Instant.now()).compareTo(CACHE_DURATION) < 0;
    }
}