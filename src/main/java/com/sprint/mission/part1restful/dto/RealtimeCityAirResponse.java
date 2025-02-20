package com.sprint.mission.part1restful.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RealtimeCityAirResponse {
    @JsonProperty("RealtimeCityAir")
    private RealtimeCityAirData realtimeCityAir;

    @Getter
    @Setter
    public static class RealtimeCityAirData {
        // 도시별 대기질 정보
        @JsonProperty("row")
        private List<Row> row;
    }

    @Getter
    @Setter
    public static class Row {
        
        // 도시 이름
        @JsonProperty("MSRSTE_NM")
        private String msrsteNm;

        // 해당 도시의 미세먼지 농도(PM2.5)
        @JsonProperty("PM25")
        private Double pm25;
    }
}
