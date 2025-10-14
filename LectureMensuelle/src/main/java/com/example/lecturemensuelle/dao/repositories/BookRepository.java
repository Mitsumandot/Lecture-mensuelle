package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookUser, Long> {
}
