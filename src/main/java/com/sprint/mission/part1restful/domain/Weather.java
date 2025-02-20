package com.sprint.mission.part1restful.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 구 이름
    @Column(name = "msrstename")
    private String msrstename;

    
    // 대기질 등급
    @Column(name = "grade")
    private String grade;

    // 데이터가 기록된 시간
    @Getter
    @Column(name = "recorded_at")
    private Instant recordedAt;

}
