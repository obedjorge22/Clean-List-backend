package com.example.CleanList.repositories;

import com.example.CleanList.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByToken(String token);

    boolean existsByToken(String token);

    Optional<Session> findByIdAndDeletedAtIsNull(Long id);

    List<Session> findAllByDeletedAtIsNull();

    List<Session> findAllByDeletedAtIsNotNull();

    List<Session> findByUser_Id(Long id);

    List<Session> findByUser_IdAndDeletedAtIsNull(Long id);
}