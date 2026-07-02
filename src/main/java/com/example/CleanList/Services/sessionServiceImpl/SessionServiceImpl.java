package com.example.CleanList.Services.sessionServiceImpl;

import com.example.CleanList.Services.SessionService;
import com.example.CleanList.dto.session.SessionResponseDTO;
import com.example.CleanList.entities.Session;
import com.example.CleanList.exception.SessionNotFoundException;
import com.example.CleanList.mappers.SessionMapper;
import com.example.CleanList.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;
    private final SessionMapper mapper;

    @Override
    public SessionResponseDTO findById(Long id) {

        Session session = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(SessionNotFoundException::new);

        return mapper.toResponse(session);
    }

    @Override
    public List<SessionResponseDTO> findAll() {

        return repository.findAllByDeletedAtIsNull()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<SessionResponseDTO> findTrash() {
        return repository.findAllByDeletedAtIsNotNull()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void revoke(Long id) {

        Session session = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(SessionNotFoundException::new);
        session.setRevoked(true);
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

        Session session = repository.findById(id)
                .orElseThrow(SessionNotFoundException::new);

        repository.delete(session);
    }
}