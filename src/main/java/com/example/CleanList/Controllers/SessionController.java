package com.example.CleanList.Controllers;

import com.example.CleanList.Services.SessionService;
import com.example.CleanList.dto.session.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService service;

    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> listAllActive() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/trash")
    public ResponseEntity<List<SessionResponseDTO>> listTrash() {
        return ResponseEntity.ok(service.findTrash());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revoke(@PathVariable Long id) {
        service.revoke(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}