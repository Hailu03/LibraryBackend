package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.BorrowedBookDto;
import com.library.programmingexercise.dto.LendDto;
import com.library.programmingexercise.dto.UserBorrowedBookDto;
import com.library.programmingexercise.entity.*;
import com.library.programmingexercise.exception.ResourceNotFoundException;
import com.library.programmingexercise.mapper.BorrowedBookMapper;
import com.library.programmingexercise.mapper.LendMapper;
import com.library.programmingexercise.mapper.UserBorrowedBookMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.repository.ReaderInfoRepository;
import com.library.programmingexercise.repository.RentRepository;
import com.library.programmingexercise.repository.RentrecordRepository;
import com.library.programmingexercise.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderInfoRepository readerInfoRepository;

    @Autowired
    private RentrecordRepository rentrecordRepository;

    // Implement the getBorrowedBooksDetails method here
    public List<BorrowedBookDto> getBorrowedBooksDetails() {
        List<Object[]> borrowedBooks = rentRepository.findReaderBorrowedBooksDetails()
                .orElseThrow(() -> new ResourceNotFoundException("There is no people borrowing book currently"));
        return BorrowedBookMapper.mapToBorrowedBookDtoList(borrowedBooks);
    }

    // Implement the getOneUserAllBorrowedBooks method here
    @Override
    public List<UserBorrowedBookDto> getOneUserAllBorrowedBooks(int userId) {
        List<Object[]> userBorrowedBooks = rentRepository.findUserBorrowedBooksDetails(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User has not borrowed any books"));
        return UserBorrowedBookMapper.mapToUserBorrowedBookDtoList(userBorrowedBooks);
    }

    // Implement the lendBook method here
    @Override
    public LendDto lendBook(String firstName, String lastName, String bookTitle, LocalDate reserveDate, LocalDate dueDate) {

        ReadersInfo readerInfo = readerInfoRepository.findReadersinfoByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ExpressionException("Reader does not exist with the given name: " + firstName + " " + lastName));

        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new ExpressionException("Book does not exist with the given title: " + bookTitle));

        // Check if the reader has already borrowed the book
        if (isReaderLendThisBook(readerInfo, book)) {
            throw new RuntimeException("You have already borrowed this book.");
        }

        Rent savedRent;
        RentRecord savedRentRecord;

        if (book.getAvailable() > 0) {
            Rent rent = new Rent();
            RentId rentId = new RentId();
            rentId.setBookID(book.getId());
            rentId.setReaderID(readerInfo.getId());
            rent.setId(rentId);
            rent.setBookID(book);
            rent.setReaderID(readerInfo);
            rent.setReserveDate(reserveDate);
            rent.setDueDate(dueDate);

            System.out.println("rentid = " + rentId.getRentID());

            savedRent = rentRepository.save(rent);

            RentRecord rentrecord = new RentRecord();
            RentRecordId rentRecordId = new RentRecordId();

            rentRecordId.setBookID(book.getId());
            rentRecordId.setReaderID(readerInfo.getId());
            rentrecord.setId(rentRecordId);
            rentrecord.setBookID(book);
            rentrecord.setReaderID(readerInfo);
            rentrecord.setReserveDate(reserveDate);
            rentrecord.setDueDate(dueDate);

            System.out.println("readerInfo.getId() = " + readerInfo.getId());
            System.out.println("book.getId() = " + book.getId());
            System.out.println("rentrecordid = " + rentRecordId.getRentID());

            savedRentRecord = rentrecordRepository.save(rentrecord);

            // Decrease the available count of the book
            book.setAvailable(book.getAvailable() - 1);
            bookRepository.save(book);

            return LendMapper.mapToLendDto(new Object[]{
                    readerInfo.getId(),
                    book.getId(),
                    readerInfo.getFirstName(),
                    readerInfo.getLastName(),
                    book.getTitle(),
                    savedRent.getReserveDate(),
                    savedRent.getDueDate()
            });
        }
        // throw exception if book is not available
        throw new ResourceNotFoundException("Book is not available");
    }

    // Implement the method to check if a reader has borrowed a book
    public boolean isReaderLendThisBook(ReadersInfo readerInfo, Book book) {
        List<Rent> rents = rentRepository.findAllByBookIDAndReaderID(book, readerInfo);
        return !rents.isEmpty();
    }
}
