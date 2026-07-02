package com.example.CleanList.repositories;

import com.example.CleanList.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Boolean existsUsersByEmail(String email);
    List<Users> findAllByDeletedAtIsNull(Long groupId);
    List<Users> findAllByDeletedAtIsNotNull(Long groupId);
}
