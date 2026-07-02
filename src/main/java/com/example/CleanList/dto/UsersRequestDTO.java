package com.example.CleanList.dto;

import com.example.CleanList.entities.enums.SystemRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UsersRequestDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        String passwordHash,
        String phone,
        SystemRole systemRole
) {
}
