package com.example.lecturemensuelle.dao.services;


import com.example.lecturemensuelle.dao.entities.Author;
import com.example.lecturemensuelle.dao.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> SearchByName(String name){
        return authorRepository.findByNameContainingIgnoreCase(name);
    }



}
