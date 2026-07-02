package com.example.CleanList.dto;

import com.example.CleanList.entities.enums.SystemRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UsersResponseDTO(

        Long id,
        String firstName,
        String lastName,
        String email,
        String passwordHash,
        String phone,
        SystemRole systemRole,
        LocalDateTime createdAt,
        LocalDateTime deletedAt,
        LocalDateTime updatedAt

) {
}
