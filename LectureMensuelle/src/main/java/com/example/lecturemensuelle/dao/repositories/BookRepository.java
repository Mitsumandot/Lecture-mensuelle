package com.example.lecturemensuelle.dao.repositories;


import com.example.lecturemensuelle.dao.entities.Author;
import com.example.lecturemensuelle.dao.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.ListResourceBundle;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List <Book> findByNameContainingIgnoreCase(String name);

    List <Book> findBookByAuthor(Author author);



}
