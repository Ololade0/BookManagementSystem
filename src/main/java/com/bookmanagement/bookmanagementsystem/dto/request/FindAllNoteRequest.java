package com.bookmanagement.bookmanagementsystem.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FindAllNoteRequest {
       private  int page;
    private int limit;
}
