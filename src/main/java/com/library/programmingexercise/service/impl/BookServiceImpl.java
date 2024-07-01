package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.exception.ResourceNotFoundException;
import com.library.programmingexercise.mapper.BookMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.service.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    // Create a logger
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    // Implement the method to add a book
    @Override
    public Book addBook(Book book) {
        // Check if a book with the same ISBN already exists
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            // Handle the case of duplication
            throw new IllegalStateException("A book with the same ISBN already exists.");
        }
        // If no duplicate exists, save and return the new book
        return bookRepository.save(book);
    }

    // Implement the method to delete a book
    @Override
    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }
    // Implement the method to search books by title
    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title)
                .orElseThrow(() -> new ResourceNotFoundException("Book with title containing " + title + " not found"));
    }

    // Implement the method to get all books
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (logger.isDebugEnabled()) {
            books.forEach(book -> logger.debug("Book: {}", book));
        }
        return books.stream() // Creates a Stream of elements from the students List
                .map((book) -> BookMapper.maptoBookDto(book))  // Transforms each Student object to a StudentDto object using StudentMapper
                .collect(Collectors.toList()); // Collects the transformed StudentDto objects into a List
    }

    // Implement the method to update a book
    @Override
    public Book updateBook(Integer id, Book bookDetail) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book is not found with id: " + id));

        book.setAuthorName(bookDetail.getAuthorName());
        book.setAvailable(bookDetail.getAvailable());
        book.setEdition(bookDetail.getEdition());
        book.setGenre(bookDetail.getGenre());
        book.setIsbn(bookDetail.getIsbn());
        book.setPublisher(bookDetail.getPublisher());
        book.setQuantity(bookDetail.getQuantity());
        book.setTitle(bookDetail.getTitle());
        book.setYearOfPublication(bookDetail.getYearOfPublication());
        book.setImage(bookDetail.getImage());

        return bookRepository.save(book);
    }

    // Implement the method to get the top 5 most borrowed books
    @Override
    public List<BookDto> getTop5MostBorrowBooks() {
        List<Book> books = bookRepository.findTopNMostBorrowedBooks(5).
                orElseThrow(() -> new ResourceNotFoundException("There is no book borrowed"));
        return BookMapper.mapToListBookDto(books);
    }

    @Override
    public List<BookDto> getMostBorrowBooksbyGenre(String genre) {
        List<Book> books = bookRepository.findTopNMostBorrowedBooksByGenre(genre,5).
                orElseThrow(() -> new ResourceNotFoundException("There is no book borrowed"));
        return BookMapper.mapToListBookDto(books);
    }

    // Implement the method to get the top 8 most borrowed books
    @Override
    public List<BookDto> getTop8MostBorrowBooks() {
        List<Book> books = bookRepository.findTopNMostBorrowedBooks(8).
                orElseThrow(() -> new ResourceNotFoundException("There is no book borrowed"));
        return BookMapper.mapToListBookDto(books);
    }

    // Implement the method to get the total number of books reserved today
    @Override
    public Integer getTotalBooksReservedToday() {
        return bookRepository.findTotalBooksReservedToday(LocalDate.now()).
                orElseThrow(() -> new ResourceNotFoundException("There is no book reserved today"));
    }

    // Implement the method to get all books sorted
    @Override
    public List<BookDto> getAllSortedBooks() {
        List<Book> books = bookRepository.findAll();
        if (logger.isDebugEnabled()) {
            books.forEach(book -> logger.debug("Book: {}", book));
        }

        return books.stream() // Creates a Stream of elements from the books List
                .sorted(Comparator.comparing(Book::getYearOfPublication).reversed()) // Sorts the books based on yearOfPublication
                .map(BookMapper::maptoBookDto)  // Transforms each Book object to a BookDto object using BookMapper
                .collect(Collectors.toList()); // Collects the transformed BookDto objects into a List
    }

    @Override
    public Integer findNumberOfTimeBookIsBorrowedById(Integer id) {
        Integer numberOfTimesBorrow = bookRepository.findNumberOfTimeBookIsBorrowedById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = "+id+" has not been borrowed yet"));
        return  numberOfTimesBorrow;
    }
}
