package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.ReturnBook;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

// an interface for the ReturnBookRepository
@Repository
public interface ReturnBookRepository extends JpaRepository<ReturnBook, Long> {
    // count return book by date
    @Query(value = "SELECT COUNT(*) " +
            "FROM returnbook r " +
            "WHERE r.return_date = :date", nativeQuery = true)
    Optional<Integer> countReturnBookByDate(@Param("date") LocalDate date);
}