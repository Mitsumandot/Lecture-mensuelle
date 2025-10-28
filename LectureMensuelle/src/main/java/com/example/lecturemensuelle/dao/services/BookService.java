package com.example.lecturemensuelle.dao.services;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.BookUser;
import com.example.lecturemensuelle.dao.repositories.BookRepository;
import com.example.lecturemensuelle.dao.repositories.BookUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final BookUserRepository bookUserRepository;


    public List<Book> SearchByName(String name){
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public double getAverage(Book book){
        List <BookUser> bookUsers = bookUserRepository.findByBook(book);
        int size = bookUsers.size();
        if(size == 0) return 0;
        double average = 0;
        for (BookUser bookUser : bookUsers) {
            average += bookUser.getRating();
        }
        return average/size;
    }

}
