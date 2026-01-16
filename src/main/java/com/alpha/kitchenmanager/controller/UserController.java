package com.alpha.kitchenmanager.controller;

import com.alpha.kitchenmanager.dto.request.UserRequestDTO;
import com.alpha.kitchenmanager.dto.response.UserResponseDTO;
import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO requestDto){
        UserResponseDTO response = userService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        log.info("Fetching all users");
        return ResponseEntity.ok(userService.findAll());
    }
}
