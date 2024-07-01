package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    // API for adding a book
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Return a response indicating the book already exists
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // API for deleting a book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Handle the case where the book with the specified ID does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // API for searching books by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // API for getting all books
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // API for updating a book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetail){
        Book updateBook = bookService.updateBook(id, bookDetail);
        return ResponseEntity.ok(updateBook);
    }

    // API for getting top 5 most borrowed books
    @GetMapping("/top5")
    public ResponseEntity<List<BookDto>> getTop5MostBorrowBooks() {
        List<BookDto> books = bookService.getTop5MostBorrowBooks();
        return ResponseEntity.ok(books);
    }

    // API for getting top 8 most borrowed books
    @GetMapping("/top8")
    public ResponseEntity<List<BookDto>> getTop8MostBorrowBooks() {
        List<BookDto> books = bookService.getTop8MostBorrowBooks();
        return ResponseEntity.ok(books);
    }

    // API for getting a reserved book by ID
    @GetMapping("/totalReservedToday")
    public ResponseEntity<Integer> getTotalBooksReservedToday() {
        Integer totalBooksReservedToday = bookService.getTotalBooksReservedToday();
        return ResponseEntity.ok(totalBooksReservedToday);
    }

    // API for sorting books by release date
    @GetMapping("/sort-by-release-date")
    public ResponseEntity<List<BookDto>> sortBooksByReleaseDate() {
        List<BookDto> books = bookService.getAllSortedBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/number-of-time-borrow/{id}")
    public Integer getNumberOfTimeBookBorrowedById(@PathVariable("id") Integer bookId){
        Integer numberOfTimeBorrowed = bookService.findNumberOfTimeBookIsBorrowedById(bookId);
        return numberOfTimeBorrowed;
    }

    @GetMapping("/top-books-by-genre/{genre}")
    public ResponseEntity<List<BookDto>> getTopBooksByGenre(@PathVariable("genre") String genre){
        List<BookDto> books = bookService.getMostBorrowBooksbyGenre(genre);
        return ResponseEntity.ok(books);
    }
}
