package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.Entity.User;
import com.sprint.mission.part1restful.dto.UserCreateRequest;
import com.sprint.mission.part1restful.dto.UserUpdateRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    @Transactional
    User createUser(UserCreateRequest userCreateDto);

    @Transactional
    Optional<User> updateUser(Long id, UserUpdateRequest userUpdateDto);

    @Transactional
    void deleteUser(Long id);
}
