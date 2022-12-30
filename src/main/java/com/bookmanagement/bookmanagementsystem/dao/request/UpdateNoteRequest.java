package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNoteRequest {
    private Long userId;
    private String title;
    private String body;
    private String content;
    private LocalDateTime updatedAt;

}
