package com.bookmanagement.bookmanagementsystem.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCannotBeFoundException extends RuntimeException {
    private int statusCode;
    public UserCannotBeFoundException(String message) {
        super(message);
    }

    public UserCannotBeFoundException(String message, int statuscode) {
        super(message);
        this.statusCode = statuscode;
    }



    public static String UserCannotBeFoundException(Long userId){
        return "User with " + userId + " Cannot be found";
    }

    public static String UserCannotBeFoundException(String email){
        return "User with " + email + " Cannot be found";
    }

}
