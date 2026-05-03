package com.alpha.kitchenmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseRequestDTO {
    @NotBlank@NotBlank(message = "House name is required")
    private String name;
    private String address;
}
