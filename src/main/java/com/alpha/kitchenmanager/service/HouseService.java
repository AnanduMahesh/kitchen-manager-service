package com.alpha.kitchenmanager.service;

import com.alpha.kitchenmanager.entity.House;

public interface HouseService {
    House createHouse(String name, String address, Long userId);
}
