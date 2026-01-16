package com.alpha.kitchenmanager.dto.response;

import com.alpha.kitchenmanager.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private UserRole role;

}
