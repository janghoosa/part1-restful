package com.sprint.mission.part1restful.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {
    @Schema(description = "사용자 이름", example = "janghoosa")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "사용자 이메일", example = "janghoosa@example.com")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}


