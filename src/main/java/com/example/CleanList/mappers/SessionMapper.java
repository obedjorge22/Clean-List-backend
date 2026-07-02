package com.example.CleanList.mappers;

import com.example.CleanList.dto.session.SessionResponseDTO;
import com.example.CleanList.entities.Session;

public class SessionMapper {

    public static SessionResponseDTO toResponse(Session session){
        return SessionResponseDTO.builder()
                .id(session.getId())
                .user(session.getUser())
                .token(session.getToken())
                .ipAddress(session.getIpAddress())
                .expiresAt(session.getExpiresAt())
                .lastUsed(session.getLastUsed())
                .deletedAt(session.getDeletedAt())
                .updatedAt(session.getUpdatedAt())
                .createdAt(session.getCreatedAt())
                .build();
    }
}
