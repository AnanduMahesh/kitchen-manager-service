package com.alpha.kitchenmanager.service.impl;

import com.alpha.kitchenmanager.dto.request.LoginRequestDTO;
import com.alpha.kitchenmanager.dto.request.LoginResponseDTO;
import com.alpha.kitchenmanager.dto.request.UserRequestDTO;
import com.alpha.kitchenmanager.dto.response.UserResponseDTO;
import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.exception.InvalidCredentialsException;
import com.alpha.kitchenmanager.exception.ResourceAlreadyExistsException;
import com.alpha.kitchenmanager.repository.UserRepository;
import com.alpha.kitchenmanager.security.JwtUtil;
import com.alpha.kitchenmanager.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder  passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        if(repo.existsByEmail(dto.getEmail())){
            throw new ResourceAlreadyExistsException(
                    "User already exists with email: " + dto.getEmail());
        }

        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return new LoginResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
