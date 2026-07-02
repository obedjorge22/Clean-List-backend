package com.example.CleanList.dto.users;

import com.example.CleanList.entities.enums.SystemRole;
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
