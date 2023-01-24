package com.bookmanagement.bookmanagementsystem.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNoteResponse {
    private Long id;
    private String message;
    private String content;
}
