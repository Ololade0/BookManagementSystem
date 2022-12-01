package com.bookmanagement.bookmanagementsystem.dao.request;

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
