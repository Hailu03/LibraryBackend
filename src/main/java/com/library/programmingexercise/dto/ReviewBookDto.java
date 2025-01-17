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
public class ReviewBookDto {
    private LocalDate date;
    private int rating;
    private String review;
    private int bookID;
    private int readerID;
}
