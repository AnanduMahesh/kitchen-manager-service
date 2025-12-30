package com.alpha.kitchenmanager.service;

import com.alpha.kitchenmanager.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findAll();
}
