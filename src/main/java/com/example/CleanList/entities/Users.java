package com.example.CleanList.entities;

import com.example.CleanList.entities.enums.SystemRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Users extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phone;
    private SystemRole systemRole;
    private boolean active;
}
