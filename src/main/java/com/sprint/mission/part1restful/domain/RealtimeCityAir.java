package com.sprint.mission.part1restful.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RealtimeCityAir {
    @JsonProperty("RealtimeCityAir")
    private RealtimeCityAir realtimeCityAir;

    @Getter
    @Setter
    public static class RealtimeCityAir {
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