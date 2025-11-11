package com.example.lecturemensuelle.controller;

import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.UserRepository;
import com.example.lecturemensuelle.dao.services.UserService;
import com.example.lecturemensuelle.dto.CurrentUserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser(){
        User user = userService.getCurrentUser();
        CurrentUserDto currentUserDto = new CurrentUserDto(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(currentUserDto);
    }



    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
