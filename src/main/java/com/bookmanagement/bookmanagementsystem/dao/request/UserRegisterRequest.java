package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterRequest {
    private String name;
    private String email;
    private String phonenumber;
    private String password;
}
