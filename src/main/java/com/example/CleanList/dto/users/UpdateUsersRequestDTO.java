package com.example.CleanList.dto.users;

import com.example.CleanList.entities.enums.SystemRole;
import jakarta.validation.constraints.Email;

public record UpdateUsersRequestDTO(
        String firstName,
        String lastName,
        @Email
        String email,
        String passwordHash,
        String phone,
        SystemRole systemRole
) {
}
