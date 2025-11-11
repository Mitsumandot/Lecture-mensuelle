package com.example.lecturemensuelle.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserBookRequest {
    private Integer rating;

    private String review;

    private String reviewTitle;

    private String status;

    private boolean favourite;

}
