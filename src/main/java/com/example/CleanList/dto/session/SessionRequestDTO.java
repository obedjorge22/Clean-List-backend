package com.example.CleanList.dto.session;

import lombok.Builder;


public record SessionRequestDTO(

        Long userId,
        String ipAddress

) {
}
