package com.library.programmingexercise.repository;


import com.library.programmingexercise.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// an interface for the BookRepository
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<List<Book>> findByTitleContaining(String title); // find books by title
    Optional<Book> findByIsbn(String isbn); // find book by ISBN
    Optional<Book> findByTitle(String title); // find book by title

    // Find the top N most borrowed books
    @Query(value = "SELECT b.* " +
            "FROM books b " +
            "JOIN rentrecord r ON r.bookID = b.bookID " +
            "GROUP BY r.bookID " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT :limit", nativeQuery = true)
    Optional<List<Book>> findTopNMostBorrowedBooks(@Param("limit") int limit);

    // Find the total number of books reserved today
    @Query(value = "SELECT COUNT(*) " +
            "FROM rentrecord r " +
            "WHERE r.reserve_date = :date", nativeQuery = true)
    Optional<Integer> findTotalBooksReservedToday(@Param("date") LocalDate date);

    @Query(value = "SELECT count(*) " +
            "from books b " +
            "join rentrecord r on r.bookID = b.bookID " +
            "where b.bookID = :id " +
            "group by r.bookID " +
            "order by count(*) " +
            ";", nativeQuery = true)
    Optional<Integer> findNumberOfTimeBookIsBorrowedById(@Param("id") int id);

    @Query(value = "SELECT b.* " +
            "FROM books b " +
            "JOIN rentrecord r ON r.bookID = b.bookID " +
            "WHERE b.genre LIKE CONCAT('%', :genre, '%') " +  // Filter by genre in the comma-separated list
            "GROUP BY b.bookID " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT :limit", nativeQuery = true)
    Optional<List<Book>> findTopNMostBorrowedBooksByGenre(@Param("genre") String genre, @Param("limit") int limit);



}

