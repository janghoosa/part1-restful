package com.sprint.mission.part1restful.service;

import com.sprint.mission.part1restful.Entity.User;
import com.sprint.mission.part1restful.dto.UserCreateRequest;
import com.sprint.mission.part1restful.dto.UserUpdateRequest;
import com.sprint.mission.part1restful.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public User createUser(UserCreateRequest userCreateDto) {
        User user = User.builder()
                .username(userCreateDto.getUsername())
                .email(userCreateDto.getEmail())
                .password(userCreateDto.getPassword())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<User> updateUser(Long id, UserUpdateRequest userUpdateDto) {
        return userRepository.findById(id).map(user -> {
            user = User.builder()
                    .id(user.getId())
                    .username(userUpdateDto.getUsername())
                    .email(userUpdateDto.getEmail())
                    .password(user.getPassword()) // 기존 비밀번호 유지
                    .createdAt(user.getCreatedAt())
                    .updatedAt(Instant.now())
                    .build();
            return userRepository.save(user);
        });
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

