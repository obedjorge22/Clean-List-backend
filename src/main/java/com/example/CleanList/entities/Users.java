package com.example.CleanList.entities;

import com.example.CleanList.entities.enums.SystemRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
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

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Session> sessions = new ArrayList<>();
}
