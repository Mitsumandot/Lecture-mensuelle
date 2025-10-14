package com.example.lecturemensuelle.dao.services;

import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
}
