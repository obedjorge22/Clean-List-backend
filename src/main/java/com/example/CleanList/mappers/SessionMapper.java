package com.example.CleanList.mappers;

import com.example.CleanList.dto.session.SessionResponseDTO;
import com.example.CleanList.entities.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class SessionMapper {

    public SessionResponseDTO toResponse(Session session) {

        return SessionResponseDTO.builder()
                .id(session.getId())
                .userId(session.getUser().getId())
                .token(session.getToken())
                .ipAddress(session.getIpAddress())
                .expiresAt(session.getExpiresAt())
                .lastUsed(session.getLastUsed())
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .deletedAt(session.getDeletedAt())
                .build();
    }
}
