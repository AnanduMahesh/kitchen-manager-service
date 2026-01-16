package com.alpha.kitchenmanager.service.impl;

import com.alpha.kitchenmanager.dto.request.UserRequestDTO;
import com.alpha.kitchenmanager.dto.response.UserResponseDTO;
import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.repository.UserRepository;
import com.alpha.kitchenmanager.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        newUser.setRole(dto.getRole());
        User savedUser = repo.save(newUser);
        return mapToResponse(savedUser);
    }

    private UserResponseDTO mapToResponse(User savedUser) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        dto.setEmail(savedUser.getEmail());
        dto.setRole(savedUser.getRole());
        return dto;
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return repo.findAll().stream().map(this::mapToResponse).toList();
    }
}
