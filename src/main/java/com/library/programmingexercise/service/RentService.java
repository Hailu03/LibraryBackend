package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.BorrowedBookDto;
import com.library.programmingexercise.dto.LendDto;
import com.library.programmingexercise.dto.UserBorrowedBookDto;

import java.time.LocalDate;
import java.util.List;

// an interface for the RentService
public interface RentService {
    List<BorrowedBookDto> getBorrowedBooksDetails();

    List<UserBorrowedBookDto> getOneUserAllBorrowedBooks(int userId);

    LendDto lendBook(String firstName, String lastName, String bookTitle, LocalDate reserveDate, LocalDate dueDate);
}