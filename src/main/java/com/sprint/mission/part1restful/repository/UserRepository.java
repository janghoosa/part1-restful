package com.sprint.mission.part1restful.repository;

import com.sprint.mission.part1restful.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
