package com.sprint.mission.part1restful.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@Service
public class SeoulWeatherService {
    private static final String API_KEY = "76587865496d696e39337154664545";
    private static final String API_URL = "http://openapi.seoul.go.kr:8088";

    public String getWeatherData() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL);
        urlBuilder.append("/").append(URLEncoder.encode(API_KEY, "UTF-8")); // API 인증키
        urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 응답 형식
        urlBuilder.append("/").append(URLEncoder.encode("ListWeatherByAddrService", "UTF-8")); // 서비스명
        urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 요청 시작 위치
        urlBuilder.append("/").append(URLEncoder.encode("5", "UTF-8")); // 요청 종료 위치

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        int responseCode = conn.getResponseCode();
        BufferedReader rd;

        if (responseCode >= 200 && responseCode <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        return sb.toString(); // JSON 형식의 데이터 반환
    }
}
