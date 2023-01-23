package com.bookmanagement.bookmanagementsystem.dao.request;

import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import lombok.*;

import java.util.List;

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
