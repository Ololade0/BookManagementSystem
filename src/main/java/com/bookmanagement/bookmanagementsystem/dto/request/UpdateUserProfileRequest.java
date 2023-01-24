package com.bookmanagement.bookmanagementsystem.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserProfileRequest {
    private Long userId;
    private String name;
    private String email;
    private String phonenumber;
}
