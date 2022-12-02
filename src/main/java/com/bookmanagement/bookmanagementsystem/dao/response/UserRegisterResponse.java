package com.bookmanagement.bookmanagementsystem.dao.response;

import lombok.*;

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


}
