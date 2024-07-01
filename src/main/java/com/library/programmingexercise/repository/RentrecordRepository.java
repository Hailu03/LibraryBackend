package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

// an interface for the RentrecordRepository
@Repository
public interface RentrecordRepository extends JpaRepository<RentRecord, Integer> {
    Optional<RentRecord> findByReserveDateAndDueDate (LocalDate reserveDate, LocalDate dueDate);
}