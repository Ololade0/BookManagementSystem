package com.bookmanagement.bookmanagementsystem.dao.response;

import com.bookmanagement.bookmanagementsystem.dto.model.Note;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRegisterResponse {
    private String message;
    private Long userId;
    private String email;
    private String password;



}
