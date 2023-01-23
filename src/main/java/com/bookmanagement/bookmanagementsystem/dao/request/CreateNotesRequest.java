package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateNotesRequest {
    private Long userId;
     private String title;
    private String body;
    private String content;
    private LocalDateTime createdAt;
}
