package com.example.lecturemensuelle.controller;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.services.BookService;
import com.example.lecturemensuelle.dto.BookDto;
import com.example.lecturemensuelle.request.BookRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBook(@RequestParam("name") String name){
        List<BookDto> books = bookService.searchByName(name);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity<BookDto> BookInfo(@RequestParam("id") Long id){
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }
}
