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

    @Column(name = "msrstename")
    private String msrstename;

    @Column(name = "grade")
    private String grade;

    @Getter
    @Column(name = "recorded_at")
    private Instant recordedAt;

}
