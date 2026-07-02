package com.example.CleanList.Controllers;

import com.example.CleanList.Services.UserService;
import com.example.CleanList.dto.users.UpdateUsersRequestDTO;
import com.example.CleanList.dto.users.UsersRequestDTO;
import com.example.CleanList.dto.users.UsersResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/trash")
    public ResponseEntity<List<UsersResponseDTO>> findTrash() {
        return ResponseEntity.ok(userService.findTrash());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> create(
            @Valid @RequestBody UsersRequestDTO request) {

        UsersResponseDTO response = userService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUsersRequestDTO request) {

        return ResponseEntity.ok(userService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        userService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        userService.restore(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
