package com.example.CleanList.Services;

import com.example.CleanList.dto.users.UpdateUsersRequestDTO;
import com.example.CleanList.dto.users.UsersRequestDTO;
import com.example.CleanList.dto.users.UsersResponseDTO;

import java.util.List;

public interface UserService {

    UsersResponseDTO create(UsersRequestDTO requestDTO);
    UsersResponseDTO update(UpdateUsersRequestDTO requestDTO, Long id);
    UsersResponseDTO findById(Long id);
    UsersResponseDTO findByEmail(String email);
    List<UsersResponseDTO> findAll();
    List<UsersResponseDTO> findTrash();
    Void softDelete(Long id);
    Void restore(Long id);
    Void delete(Long id);

}
