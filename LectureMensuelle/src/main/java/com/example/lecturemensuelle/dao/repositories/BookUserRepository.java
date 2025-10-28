package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.BookUser;
import com.example.lecturemensuelle.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUserRepository extends JpaRepository<BookUser, Long> {

    List <BookUser> findByUser(User user);

    List <BookUser> findByBook(Book book);
}
