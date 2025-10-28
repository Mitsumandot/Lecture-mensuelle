package com.example.lecturemensuelle.dao.services;

import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
