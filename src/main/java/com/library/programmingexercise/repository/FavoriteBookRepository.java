package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// an interface for the FavoriteBookRepository
@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Integer> {
    // Find all favorite books by reader ID
    @Query(value = "SELECT b FROM FavoriteBook f JOIN Book b ON f.bookID = b.id WHERE f.readerID = :readerId")
    List<Book> findAllFavoriteBooks(@Param("readerId") Integer readerId);

    void deleteByBookIDAndReaderID(Integer bookId, Integer readerId); // delete favorite book by book ID and reader ID

}
