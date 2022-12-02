package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserProfileRequest {
    private String name;
    private String email;
    private String phonenumber;
}
