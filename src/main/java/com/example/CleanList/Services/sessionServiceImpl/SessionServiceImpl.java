package com.example.CleanList.Services.sessionServiceImpl;

import com.example.CleanList.Services.SessionService;
import com.example.CleanList.dto.session.SessionResponseDTO;
import com.example.CleanList.entities.Session;
import com.example.CleanList.entities.Users;
import com.example.CleanList.exception.SessionNotFoundException;
import com.example.CleanList.exception.UserNotFoundException;
import com.example.CleanList.mappers.SessionMapper;
import com.example.CleanList.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository repository;

    public SessionServiceImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SessionResponseDTO findById(Long id) {
        if (!repository.existsById(id)){
            throw new SessionNotFoundException();
        }
        return SessionMapper.toResponse(repository.findByIdAndDeletedAtIsNull(id));
    }

    @Override
    public List<SessionResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(SessionMapper::toResponse)
                .toList();
    }

    @Override
    public List<SessionResponseDTO> findTrash() {
        return repository.findAllByDeletedAtIsNull()
                .stream()
                .toList();
    }

    @Override
    public void revoke(Long id) {
        Session session = repository.findById(id)
                .orElseThrow(SessionNotFoundException::new);

        session.setDeletedAt(LocalDateTime.now());

        repository.save(session);
    }

    @Override
    public void restore(Long id) {

        Session session = repository.findById(id)
                .orElseThrow(SessionNotFoundException::new);

        session.setDeletedAt(null);

        repository.save(session);

    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new SessionNotFoundException();
        }
        Session session = repository.findById(id)
                .orElseThrow(SessionNotFoundException::new);
        repository.delete(session);
    }
}
