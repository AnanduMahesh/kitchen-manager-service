package com.alpha.kitchenmanager.service;

import com.alpha.kitchenmanager.dto.request.HouseRequestDTO;
import com.alpha.kitchenmanager.dto.response.HouseResponseDTO;
import com.alpha.kitchenmanager.entity.House;

public interface HouseService {
    HouseResponseDTO createHouse(HouseRequestDTO request);
}
