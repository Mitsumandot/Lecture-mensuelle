package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookUserRepository extends JpaRepository<BookUser, Long> {

}
