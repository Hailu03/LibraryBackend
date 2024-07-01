package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.ReviewBookDto;

import java.time.LocalDate;

// an interface for the ReviewBookService
public interface ReviewBookService {
    ReviewBookDto reviewBook(LocalDate date, int rating, String review, int bookID, int readerID);
}
