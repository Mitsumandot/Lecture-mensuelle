package com.example.lecturemensuelle.dao.repositories;

import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    List <UserBook> findByUser(User user);

    List <UserBook> findByBook(Book book);

    Optional <UserBook> findByUserAndBook(User user, Book book);
}
