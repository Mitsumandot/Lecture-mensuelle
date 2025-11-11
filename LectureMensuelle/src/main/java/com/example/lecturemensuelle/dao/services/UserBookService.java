package com.example.lecturemensuelle.dao.services;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.entities.User;
import com.example.lecturemensuelle.dao.repositories.UserBookRepository;
import com.example.lecturemensuelle.dto.UserBookDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final UserService userService;

    public List<UserBookDto> getAllUserBooks(User user){
        List<UserBook> books = userBookRepository.findByUser(user);
        List<UserBookDto> booksDto = new ArrayList<>();
        for(UserBook book: books){
            UserBookDto userBookDto = new UserBookDto(
                    book.getId(),
                    book.getBook().getName(),
                    book.getBook().getImage(),
                    book.getBook().getAuthor(),
                    book.getUser().getUsername(),
                    book.getReview(),
                    book.getRating(),
                    book.getReviewTitle(),
                    book.getStatus(),
                    book.isFavourite()
            );
            booksDto.add(userBookDto);
        }
        return booksDto;

    }

    public List<UserBook> getAllBookUserBooks(Book book){
        return userBookRepository.findByBook(book);
    }

    public UserBook createUserBook(UserBook userBook){
        return userBookRepository.save(userBook);
    }

}
