package com.alpha.kitchenmanager.service.impl;

import com.alpha.kitchenmanager.dto.request.HouseRequestDTO;
import com.alpha.kitchenmanager.dto.response.HouseResponseDTO;
import com.alpha.kitchenmanager.entity.House;
import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.exception.ResourceNotFoundException;
import com.alpha.kitchenmanager.repository.HouseRepository;
import com.alpha.kitchenmanager.repository.UserRepository;
import com.alpha.kitchenmanager.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public HouseResponseDTO createHouse(HouseRequestDTO request) {
        // 1️⃣ Get logged-in user email
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        // 2️⃣ Fetch creator from DB
        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3️⃣ Create house
        House house = new House();
        house.setName(request.getName());
        house.setAddress(request.getAddress());
        house.setCreatedBy(creator);

        // 4️⃣ Add creator as member
        house.getUsers().add(creator);
        creator.getHouses().add(house);
        // 5️⃣ Save
        House saved = houseRepository.save(house);

        // 6️⃣ Map to DTO
        return mapToDTO(saved);

    }

    private HouseResponseDTO mapToDTO(House house) {

        HouseResponseDTO dto = new HouseResponseDTO();
        dto.setId(house.getId());
        dto.setName(house.getName());
        dto.setAddress(house.getAddress());
        dto.setCreatedBy(house.getCreatedBy().getEmail());

        Set<String> members = house.getUsers()
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());

        dto.setMembers(members);

        return dto;
    }
}
