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
public class ReturnDto {
    private Integer readerId;
    private Integer bookId;
    private Integer rentId;
    private String firstName;
    private String lastName;
    private String bookTitle;
    private LocalDate returnDate;
}