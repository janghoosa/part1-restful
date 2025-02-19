package com.sprint.mission.part1restful.repository;

import com.sprint.mission.part1restful.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findFirstByLocationOrderByRecordedAtDesc(String location);
}
