package com.library.programmingexercise.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookDto {
    private Integer readerId;
    private String firstName;
    private String lastName;
    private String bookTitle;
    private LocalDate reserveDate;
    private LocalDate dueDate;
}