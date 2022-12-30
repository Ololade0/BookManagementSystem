package com.bookmanagement.bookmanagementsystem.dao.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNoteResponse {
    private Long id;
    private String message;
}
