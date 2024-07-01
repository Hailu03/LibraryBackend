package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.ReaderNotification;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.dto.AdminNotificationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// an interface for the ReaderNotificationRepository
@Repository
public interface ReaderNotificationRepository extends JpaRepository<ReaderNotification, Integer> {
    List<ReaderNotification> findNotificationByReaderID(int readerID); // find notification by reader ID
    @Query(value =  "SELECT rn.readerID, rn.date_time, rn.message, r.first_name, r.last_name, + b.title " +
            "FROM readersinfo r " +
            "JOIN readernotification rn ON rn.readerID = r.readerID " +
            "JOIN books b ON rn.bookID = b.bookid " +
            "ORDER BY rn.date_time DESC", nativeQuery = true)
    List<Object[]> findReaderInfoAndBookDetails();
}
