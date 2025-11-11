package com.example.lecturemensuelle.controller;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.repositories.BookRepository;
import com.example.lecturemensuelle.dao.repositories.UserBookRepository;
import com.example.lecturemensuelle.dao.repositories.UserRepository;
import com.example.lecturemensuelle.dao.services.UserBookService;
import com.example.lecturemensuelle.dao.services.UserService;
import com.example.lecturemensuelle.dto.UserBookDto;
import com.example.lecturemensuelle.request.UserBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/userbooks")
@RestController
@AllArgsConstructor
public class UserBookController {
    private final UserBookService userBookService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;

    @GetMapping("/me")
    public ResponseEntity<List<UserBookDto>> authenticatedUserBooks(){
        User user = userService.getCurrentUser();
        List<UserBookDto> booksDto = userBookService.getAllUserBooks(user);
        System.out.println(booksDto.size());
        return ResponseEntity.ok(booksDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> UserBooks(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        List<UserBookDto> booksDto = null;
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            booksDto = userBookService.getAllUserBooks(user);
        }
        return ResponseEntity.ok(booksDto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> createUserBook(@PathVariable Long id, @RequestBody UserBookRequest userBookRequest){
        System.out.println("Hello");
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            return ResponseEntity.ok(null);
        }
        Book book = optionalBook.get();
        User user = userService.getCurrentUser();
        Optional<UserBook> optionalUserBook = userBookRepository.findByUserAndBook(user, book);
        if(optionalUserBook.isPresent()){
            return this.updateUserBook(id, userBookRequest);
        }
        UserBook userBook = new UserBook();
        userBook.setRating(userBookRequest.getRating());
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setReview(userBookRequest.getReview());
        userBook.setReviewTitle(userBookRequest.getReviewTitle());
        userBook.setStatus(userBookRequest.getStatus());
        userBook.setFavourite(userBookRequest.isFavourite());
        userBook = userBookService.createUserBook(userBook);
        return ResponseEntity.ok(userBook);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUserBook(@PathVariable Long id, @RequestBody UserBookRequest userBookRequest){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            return ResponseEntity.ok(null);
        }
        User user = userService.getCurrentUser();
        Book book = optionalBook.get();
        Optional<UserBook> optionalUserBook = userBookRepository.findByUserAndBook(user, book);
        if(optionalUserBook.isEmpty()){
            return ResponseEntity.ok(null);
        }
        UserBook userBook = optionalUserBook.get();
        userBook.setRating(userBookRequest.getRating());
        userBook.setReview(userBookRequest.getReview());
        userBook.setReviewTitle(userBookRequest.getReviewTitle());
        userBook.setStatus(userBookRequest.getStatus());
        userBook.setFavourite(userBookRequest.isFavourite());
        userBook = userBookService.createUserBook(userBook);
        return ResponseEntity.ok(userBook);


    }


}
