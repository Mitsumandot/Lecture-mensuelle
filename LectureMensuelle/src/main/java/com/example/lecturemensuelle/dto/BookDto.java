package com.example.lecturemensuelle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private String description;
    private String author;
    private double average;
    private String image;
}
