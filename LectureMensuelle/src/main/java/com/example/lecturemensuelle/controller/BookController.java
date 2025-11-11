package com.example.lecturemensuelle.controller;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.services.BookService;
import com.example.lecturemensuelle.request.BookRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/book")
@RestController
@AllArgsConstructor
public class BookController {

    BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<Book> addGoogleBook(@RequestBody BookRequest bookRequest){
        Book book = bookService.addGoogleBook(bookRequest.getTitle());
        return ResponseEntity.ok(book);

    }
}
