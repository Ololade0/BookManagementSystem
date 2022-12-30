package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteNoteByIdRequest {
    private Long noteId;
    private Long userId;
}
