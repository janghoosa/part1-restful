package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.dto.RealtimeCityAirResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class WeatherService {
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/%s/json/RealtimeCityAir/1/25/";
    private final RestTemplate restTemplate;
    private final String serviceKey;

    public WeatherService(RestTemplate restTemplate, @Value("${weather.api.service-key:}") String serviceKey) {
        this.restTemplate = restTemplate;
        this.serviceKey = serviceKey;

        log.info("WeatherService initialized with serviceKey: {}", serviceKey);
    }


    /**
     * 특정 자치구(서울)의 초미세먼지(PM2.5) 농도를 조회합니다.
     *
     * @param regionName 조회할 자치구 이름
     * @return 초미세먼지 농도(Double) 또는 데이터가 없을 경우 null
     * @throws ResponseStatusException API 호출 실패 시
     */


    public Double getPm25ByRegion(String regionName) {
        if(serviceKey == null || serviceKey.isBlank()) {
            log.warn("service key is not configured");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "날씨 API 설정이 없습니다");
        }

        try {
            String url = String.format(API_URL, serviceKey);
            log.info("Calling Weather API: {}", url);
            RealtimeCityAirResponse response = restTemplate.getForObject(url, RealtimeCityAirResponse.class);
            log.info("Weather API Response: {}", response);

            if(response == null || response.getRealtimeCityAir() == null) {
                log.error("Received null response from weather API");
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "날씨 정보를 가져올 수 없습니다");
            }

            return response.getRealtimeCityAir().getRow().stream()
                    .filter(row -> row.getMsrsteNm().equalsIgnoreCase(regionName))
                    .map(RealtimeCityAirResponse.Row::getPm25)
                    .findFirst()
                    .orElse(null);
        } catch (RestClientException e) {
            log.error("Failed to call weather API", e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "날씨 API 호출 실패", e);
        }
    }
}