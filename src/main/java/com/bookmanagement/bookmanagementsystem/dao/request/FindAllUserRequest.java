package com.bookmanagement.bookmanagementsystem.dao.request;

import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllUserRequest {
        private  int numberOfPerPages;
        private int pageNumber;
   }
