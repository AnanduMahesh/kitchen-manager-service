package com.alpha.kitchenmanager.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class HouseResponseDTO {

    private Long id;
    private String name;
    private String address;
    private String createdBy;
    private Set<String> members;
}
