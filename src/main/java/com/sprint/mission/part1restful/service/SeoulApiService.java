package com.sprint.mission.part1restful.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class SeoulApiService {
    public String getCityData() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); // URL
        urlBuilder.append("/" + URLEncoder.encode("644f4a48416d696e35354f524d5a66", "UTF-8")); // 인증키
        urlBuilder.append("/" + URLEncoder.encode("xml", "UTF-8")); // 요청파일타입 (xml, json 등)
        urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew", "UTF-8")); // 서비스명
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); // 요청시작위치
        urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); // 요청종료위치
        urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8")); // 서비스별 추가 요청 인자 (예시)

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        int responseCode = conn.getResponseCode(); // 응답 코드 확인
        System.out.println("Response code: " + responseCode);

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
        return sb.toString();
    }
}
