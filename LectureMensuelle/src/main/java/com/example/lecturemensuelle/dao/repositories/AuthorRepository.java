package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
