package com.example.CleanList.mappers;

import com.example.CleanList.dto.users.UpdateUsersRequestDTO;
import com.example.CleanList.dto.users.UsersRequestDTO;
import com.example.CleanList.dto.users.UsersResponseDTO;
import com.example.CleanList.entities.Users;

public class UsersMapper {

    public static Users toEntity(UsersRequestDTO requestDTO){
        return Users.builder()
                .email(requestDTO.email())
                .firstName(requestDTO.firstName())
                .lastName(requestDTO.lastName())
                .passwordHash(requestDTO.passwordHash())
                .phone(requestDTO.phone())
                .systemRole(requestDTO.systemRole())
                .build();
    }

    public static Users toEntity(UpdateUsersRequestDTO requestDTO){
        return Users.builder()
                .email(requestDTO.email())
                .firstName(requestDTO.firstName())
                .lastName(requestDTO.lastName())
                .passwordHash(requestDTO.passwordHash())
                .phone(requestDTO.phone())
                .systemRole(requestDTO.systemRole())
                .build();
    }

    public static UsersResponseDTO toResponse(Users user){
        return UsersResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .passwordHash(user.getPasswordHash())
                .phone(user.getPhone())
                .systemRole(user.getSystemRole())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
