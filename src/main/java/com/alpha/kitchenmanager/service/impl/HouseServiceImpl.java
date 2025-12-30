package com.alpha.kitchenmanager.service.impl;

import com.alpha.kitchenmanager.entity.House;
import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.repository.HouseRepository;
import com.alpha.kitchenmanager.repository.UserRepository;
import com.alpha.kitchenmanager.service.HouseService;

public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    public HouseServiceImpl(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public House createHouse(String name, String address, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        House house = new House();
        house.setName(name);
        house.setAddress(address);
        house.setCreatedBy(user);
        return houseRepository.save(house);
    }
}
