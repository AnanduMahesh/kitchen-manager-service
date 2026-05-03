package com.alpha.kitchenmanager.controller;

import com.alpha.kitchenmanager.dto.request.HouseRequestDTO;
import com.alpha.kitchenmanager.dto.response.HouseResponseDTO;
import com.alpha.kitchenmanager.service.HouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HouseResponseDTO> createHouse(
            @Valid @RequestBody HouseRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(houseService.createHouse(request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<HouseResponseDTO>> getMyHouses() {

        return ResponseEntity.ok(houseService.getMyHouses());
    }

}
