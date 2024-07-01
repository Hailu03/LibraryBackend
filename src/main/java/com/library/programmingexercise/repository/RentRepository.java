package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.ReadersInfo;
import com.library.programmingexercise.entity.Rent;
import com.library.programmingexercise.entity.RentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// an interface for the RentRepository
@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    // Find all borrowed books details
    @Query(value = "select r1.readerID, r1.first_name, r1.last_name, b.title, r2.reserve_date, r2.due_date " +
            "from readersinfo r1 " +
            "join rent r2 on r1.readerID = r2.readerID " +
            "join books b on r2.bookID = b.bookID", nativeQuery = true)
    Optional<List<Object[]>> findReaderBorrowedBooksDetails();


    // Find all borrowed books details by user ID
    @Query(value = "SELECT r.rentID, b.image, b.title, r.reserve_date, r.due_date " +
            "FROM readersinfo r1 " +
            "JOIN rent r ON r1.readerID = r.readerID " +
            "JOIN books b ON r.bookID = b.bookID " +
            "WHERE r1.readerID = :userId", nativeQuery = true)
    Optional<List<Object[]>> findUserBorrowedBooksDetails(@Param("userId") int userId);

    // Find all borrowed books details by book ID and user ID
    List<Rent>  findAllByBookIDAndReaderID(Book bookID, ReadersInfo readerID);

    Optional<Rent> findById(RentId rentId); // find rent by ID
}