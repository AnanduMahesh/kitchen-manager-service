package com.alpha.kitchenmanager.service.impl;

import com.alpha.kitchenmanager.entity.User;
import com.alpha.kitchenmanager.repository.UserRepository;
import com.alpha.kitchenmanager.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }
}
