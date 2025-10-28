package com.example.lecturemensuelle.dao.services;


import com.example.lecturemensuelle.dao.entities.BookUser;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.BookUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookUserService {

    private final BookUserRepository bookUserRepository;

    public List<BookUser> getAllUserBooks(User user){
        return bookUserRepository.findByUser(user);
    }

}
