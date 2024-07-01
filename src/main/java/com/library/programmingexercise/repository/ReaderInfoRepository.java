package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.ReadersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderInfoRepository extends JpaRepository<ReadersInfo, Integer> {
    Optional<ReadersInfo> findByEmail(String email); // find reader by email

    // Find the top N most borrowed books
    Optional<ReadersInfo> findReadersinfoByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT r1.* " +
            "FROM readersinfo r1 " +
            "         JOIN rentrecord r2 ON r1.readerID = r2.readerID " +
            "GROUP BY r1.readerID " +
            "ORDER BY count(*) DESC " +
            "LIMIT 5;", nativeQuery = true)
    Optional<List<ReadersInfo>> findTop5MostBorrowUser(); // find the top 5 most borrowed users

    @Query(value = "SELECT count(*) " +
            "FROM readersinfo r1 " +
            "JOIN rentrecord r2 ON r1.readerID = r2.readerID " +
            "WHERE r1.readerID = :id " +
            "GROUP BY r1.readerID " +
            "ORDER BY count(*);", nativeQuery = true)
    Optional<Integer> findNumberOfTimeUserBorrowById(@Param("id") int id);
}
