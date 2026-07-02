package com.example.CleanList.Services;

import com.example.CleanList.dto.session.SessionResponseDTO;

import java.util.List;

public interface SessionService {

    SessionResponseDTO findById(Long id);

    List<SessionResponseDTO> findAll();

    List<SessionResponseDTO> findTrash();

    void revoke(Long id);

    void restore(Long id);

    void delete(Long id);
}
