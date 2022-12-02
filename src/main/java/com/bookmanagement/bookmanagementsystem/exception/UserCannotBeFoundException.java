package com.bookmanagement.bookmanagementsystem.exception;

public class UserCannotBeFoundException extends RuntimeException {
    public UserCannotBeFoundException(String message) {
        super(message);
    }
    public static String UserCannotBeFoundException(Long userId){
        return "User with " + userId + " Cannot be found";
    }

    public static String UserCannotBeFoundException(String email){
        return "User with " + email + " Cannot be found";
    }
}
