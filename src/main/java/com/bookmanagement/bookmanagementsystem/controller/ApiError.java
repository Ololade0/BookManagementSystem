package com.bookmanagement.bookmanagementsystem.controller;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private int statusCode;
    private boolean successful;

}
