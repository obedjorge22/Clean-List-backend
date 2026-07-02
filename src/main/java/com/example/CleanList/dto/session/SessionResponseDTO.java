package com.example.CleanList.dto.session;

import com.example.CleanList.entities.Users;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SessionResponseDTO(
        Long id,
        Users user,
        String ipAddress,
        String token,
        LocalDateTime expiresAt,
        LocalDateTime lastUsed,
        LocalDateTime createdAt,
        LocalDateTime deletedAt,
        LocalDateTime updatedAt
) {
}
