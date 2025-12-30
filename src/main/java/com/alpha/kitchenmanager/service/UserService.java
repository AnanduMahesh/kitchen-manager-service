package com.alpha.kitchenmanager.service;

import com.alpha.kitchenmanager.entity.User;
import org.springframework.stereotype.Service;
import com.alpha.kitchenmanager.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository repo;
    public UserService(UserRepository repo){
        this.repo=repo;
    }

    public User save(User user){
        return repo.save(user);
    }

    public List<User> findAll(){
        return repo.findAll();
    }
}
