package com.example.lecturemensuelle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.codec.StringDecoder;

@Getter
@Setter
@AllArgsConstructor
public class UserBookDto {
    private Long id;

    //book Related


    private String bookName;

    private String bookImage;

    private String author;

    //user related

    private String username;


    //user book related

    private String review;

    private Integer rating;

    private String reviewTitle;

    private String status;

    private Boolean favourite;





}
