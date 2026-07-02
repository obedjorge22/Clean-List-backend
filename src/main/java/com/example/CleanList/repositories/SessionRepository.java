package com.example.CleanList.repositories;

import com.example.CleanList.dto.session.SessionResponseDTO;
import com.example.CleanList.entities.Group;
import com.example.CleanList.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SessionRepository extends JpaRepository<Session, Long>  {

    Optional<Session> findByToken(String token);
    List<Session> findByUserId(Long id);
    List<Session> findByUserIdAndDeletedAtIsNull(Long id);
    boolean existsByToken(String token);
    Session findByIdAndDeletedAtIsNull(Long id);
    List<SessionResponseDTO> findAllByDeletedAtIsNull();
}
