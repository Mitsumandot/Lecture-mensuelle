package com.example.lecturemensuelle.dao.services;


import com.example.lecturemensuelle.dao.entities.Book;
import com.example.lecturemensuelle.dao.entities.UserBook;
import com.example.lecturemensuelle.dao.repositories.BookRepository;
import com.example.lecturemensuelle.dao.repositories.UserBookRepository;
import com.example.lecturemensuelle.dto.BookDto;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;

import javax.swing.text.html.Option;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final UserBookRepository bookUserRepository;

    public List<BookDto> searchByName(String name){
        List<Book> books = bookRepository.findByNameContainingIgnoreCase(name);
        List<BookDto> booksDto = new ArrayList<>();
        for(Book book : books){
            BookDto bookDto = new BookDto(book.getId(),
                    book.getName(),
                    book.getDescription(),
                    book.getAuthor(),
                    this.getAverage(book),
                    book.getImage());
            booksDto.add(bookDto);

        }
        return booksDto;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public double getAverage(Book book){
        List <UserBook> bookUsers = bookUserRepository.findByBook(book);
        int size = bookUsers.size();
        if(size == 0) return 0;
        double average = 0;
        for (UserBook bookUser : bookUsers) {
            average += bookUser.getRating();
        }
        return average/size;
    }

    public BookDto getBookById(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            return null;
        }
        Book book = optionalBook.get();
        return new BookDto(book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAuthor(),
                this.getAverage(book),
                book.getImage());
    }

    public Book addGoogleBook(String title){
        URL api;
        String baseURL = "https://www.googleapis.com/books/v1/volumes";
        String query = String.format("q=intitle:%s&maxResults=10",
                URLEncoder.encode(title, StandardCharsets.UTF_8));
        HttpURLConnection connexion;
        try {
            api = new URL(baseURL + "?" + query);
            System.out.println("Final URL: " + api);
            connexion = (HttpURLConnection) api.openConnection();
            connexion.setRequestMethod("GET");
            System.out.println("bienvenue a google api");

            int status = connexion.getResponseCode();

            Reader streamReader = null;

            if (status > 299) {
                streamReader = new InputStreamReader(connexion.getErrorStream());
            } else {
                streamReader = new InputStreamReader(connexion.getInputStream());
            }

            BufferedReader in = new BufferedReader(streamReader);
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String contentString = content.toString();

            JSONObject response = new JSONObject(contentString);

            System.out.println(response);

            JSONArray items = response.getJSONArray("items");

            String name = "";
            String description = "";
            String author = "";
            String image = "";
            JSONArray authors = null;
            JSONObject images = null;


            for(int i = 0; i < items.toList().size(); i++){

                if(!Objects.equals(name, "") &&
                        !Objects.equals(description, "") &&
                        !Objects.equals(author, "") &&
                !Objects.equals(image, "")){
                    break;
                }

                JSONObject item = items.getJSONObject(i);

                JSONObject book = null;
                if (!item.has("volumeInfo")) {
                    continue;
                }

                book = item.getJSONObject("volumeInfo");

                if (Objects.equals(name, "") && book.has("title")) {
                    name = book.getString("title");
                }

                if(Objects.equals(description, "") && book.has("description")){
                    description = book.getString("description");
                }

                if(Objects.equals(image, "") && book.has("imageLinks")){
                    images = book.getJSONObject("imageLinks");

                    if(images.has("smallThumbnail")){
                        image = images.getString("smallThumbnail");
                    }

                    if(images.has("thumbnail")){
                        image = images.getString("thumbnail");
                    }
                }


                if(book.has("authors")){
                    authors = book.getJSONArray("authors");
                }

                if (authors != null && Objects.equals(author, "")) {
                    author = authors.getString(0);
                }
            }

            Book book = new Book();
            book.setName(name);
            book.setImage(image);
            book.setAuthor(author);
            book.setDescription(description);

            return bookRepository.save(book);





        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }



}
