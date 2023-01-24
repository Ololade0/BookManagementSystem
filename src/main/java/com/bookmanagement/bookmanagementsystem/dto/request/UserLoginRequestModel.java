package com.bookmanagement.bookmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequestModel {
    private String email;
    private String password;

}
