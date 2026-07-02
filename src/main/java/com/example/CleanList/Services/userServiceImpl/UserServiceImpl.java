package com.example.CleanList.Services.userServiceImpl;

import com.example.CleanList.Services.UserService;
import com.example.CleanList.dto.UpdateUsersRequestDTO;
import com.example.CleanList.dto.UsersRequestDTO;
import com.example.CleanList.dto.UsersResponseDTO;
import com.example.CleanList.entities.Users;
import com.example.CleanList.entities.enums.SystemRole;
import com.example.CleanList.exception.EmailAlreadyExistsException;
import com.example.CleanList.exception.PhoneAlreadyExistsException;
import com.example.CleanList.exception.UserNotFoundException;
import com.example.CleanList.mappers.UsersMapper;
import com.example.CleanList.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersResponseDTO create(UsersRequestDTO requestDTO) {
        if (repository.existsUsersByEmail(requestDTO.email()) || repository.existsUsersByPhone(requestDTO.phone()) ){
            throw new EmailAlreadyExistsException(requestDTO.email());
        }

        if (repository.existsUsersByPhone(requestDTO.phone()) ){
            throw new PhoneAlreadyExistsException(requestDTO.phone());
        }

        Users user = UsersMapper.toEntity(requestDTO);

        user.setPasswordHash(passwordEncoder.encode(requestDTO.passwordHash()));
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setSystemRole(SystemRole.USER);
        repository.save(user);
        return UsersMapper.toResponse(user);

    }



    @Override
    public UsersResponseDTO update(UpdateUsersRequestDTO requestDTO, Long id) {
        if (!repository.existsById(id)){
            throw new UserNotFoundException();
        }

        Users users = repository.findUsersById(id);
        users.setEmail(requestDTO.email());
        users.setSystemRole(requestDTO.systemRole());
        users.setFirstName(requestDTO.firstName());
        users.setLastName(requestDTO.lastName());
        users.setPhone(requestDTO.phone());
        users.setUpdatedAt(LocalDateTime.now());
        repository.save(users);

        return UsersMapper.toResponse(users);
    }

    @Override
    public UsersResponseDTO findById(Long id) {
        if (!repository.existsById(id)){
            throw new UserNotFoundException();
        }
        return UsersMapper.toResponse(repository.findUsersById(id));
    }

    @Override
    public UsersResponseDTO findByEmail(String email) {
        if (!repository.existsUsersByEmail(email)){
            throw new UserNotFoundException();
        }
        return UsersMapper.toResponse(repository.findByEmail(email));
    }

    @Override
    public List<UsersResponseDTO> findAll() {
        return repository.findAllByDeletedAtIsNotNull()
                .stream()
                .map(UsersMapper::toResponse)
                .toList();
    }

    @Override
    public List<UsersResponseDTO> findTrash() {
        return repository.findAllByDeletedAtIsNull()
                .stream()
                .map(UsersMapper::toResponse)
                .toList();
    }

    @Override
    public Void softDelete(Long id) {

        if (!repository.existsById(id)){
            throw new UserNotFoundException();
        }
        Users users = repository.findUsersById(id);
        users.setDeletedAt(LocalDateTime.now());
        return null;

    }

    @Override
    public Void restore(Long id) {

        if (!repository.existsById(id)){
            throw new UserNotFoundException();
        }
        Users users = repository.findUsersById(id);
        users.setDeletedAt(null);
        return null;
    }

    @Override
    public Void delete(Long id) {
        if (!repository.existsById(id)){
            throw new UserNotFoundException();
        }
        Users users = repository.findUsersById(id);
        repository.delete(users);
        return null;
    }
}
