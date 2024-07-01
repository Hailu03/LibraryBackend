package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.ReturnDto;

import java.time.LocalDate;

// an interface for the ReturnService
public interface ReturnService {
    ReturnDto returnBook(String firstName, String lastName, String bookTitle, Integer rentId, LocalDate returnDate);

    Integer countReturnBookByDate();
}