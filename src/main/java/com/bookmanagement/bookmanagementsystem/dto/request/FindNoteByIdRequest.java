package com.bookmanagement.bookmanagementsystem.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FindNoteByIdRequest {
    private Long userId;
    private Long noteId;
}
