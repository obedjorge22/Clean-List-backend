package com.example.CleanList.dto;

import com.example.CleanList.entities.Users;
import lombok.Builder;

import java.util.List;
@Builder
public record Response(String status, String message, String groupName, List<Users> users) {
}
