package com.example.lecturemensuelle.dao.services;

import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(userRepository.findById(user.getId()).isPresent()){
            user = userRepository.findById(user.getId()).get();
        }
        else{
            throw new RuntimeException("User not found");
        }
        return user;

    }




}
