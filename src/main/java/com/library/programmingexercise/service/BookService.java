package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Book;

import java.util.List;

// an interface for the BookService
public interface BookService {
    Book addBook(Book book);

    void deleteBook(int bookId);

    List<Book> searchBooksByTitle(String title);

    List<BookDto> getAllBooks();

    Book updateBook(Integer id, Book bookDetail);

    List<BookDto> getTop5MostBorrowBooks();

    List<BookDto> getMostBorrowBooksbyGenre(String genre);

    List<BookDto> getTop8MostBorrowBooks();

    Integer getTotalBooksReservedToday();
    List<BookDto> getAllSortedBooks();
    Integer findNumberOfTimeBookIsBorrowedById(Integer id);
}
