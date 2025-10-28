package com.example.lecturemensuelle.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String JwtToken;
    private long expiresIn;
}
