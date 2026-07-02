package com.example.CleanList.repositories;

import com.example.CleanList.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Boolean existsUsersByEmail(String email);
    Boolean existsUsersByPhone(String phone);
    List<Users> findAllByDeletedAtIsNull();
    List<Users> findAllByDeletedAtIsNotNull();
    Users findUsersById(Long id);

}
