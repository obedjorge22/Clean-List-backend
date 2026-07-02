package com.example.CleanList.dto.session;

import lombok.Builder;

import java.time.LocalDateTime;


public record SessionResponseDTO(
        Long id,
        Long userid,
        String ipAddress,
        String token,
        LocalDateTime expiresAt,
        LocalDateTime lastUsed,
        LocalDateTime createdAt,
        LocalDateTime deletedAt,
        LocalDateTime updatedAt
) {
}
