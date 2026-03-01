package com.alpha.kitchenmanager.service;

import com.alpha.kitchenmanager.dto.request.LoginRequestDTO;
import com.alpha.kitchenmanager.dto.request.LoginResponseDTO;
import com.alpha.kitchenmanager.dto.request.UserRequestDTO;
import com.alpha.kitchenmanager.dto.response.UserResponseDTO;
import com.alpha.kitchenmanager.entity.User;

import java.util.List;

public interface UserService {
//    User save(User user);
//
//    List<User> findAll();
    UserResponseDTO save(UserRequestDTO dto);
    List<UserResponseDTO> findAll();
    LoginResponseDTO login(LoginRequestDTO request);
}
