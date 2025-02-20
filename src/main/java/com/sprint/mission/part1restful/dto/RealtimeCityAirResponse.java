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
        @JsonProperty("row")
        private List<Row> row;
    }

    @Getter
    @Setter
    public static class Row {
        @JsonProperty("MSRSTE_NM")
        private String msrsteNm;

        @JsonProperty("PM25")
        private Double pm25;
    }
}
