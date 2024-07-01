package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.ReturnDto;
import com.library.programmingexercise.entity.*;
import com.library.programmingexercise.exception.ResourceNotFoundException;
import com.library.programmingexercise.mapper.ReturnMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.repository.ReaderInfoRepository;
import com.library.programmingexercise.repository.RentRepository;
import com.library.programmingexercise.repository.ReturnBookRepository;
import com.library.programmingexercise.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private BookRepository bookRepository; // Inject the BookRepository

    @Autowired
    private ReaderInfoRepository readerInfoRepository; // Inject the ReaderInfoRepository

    @Autowired
    private RentRepository rentRepository; // Inject the RentRepository

    @Autowired
    private ReturnBookRepository returnBookRepository; // Inject the ReturnBookRepository

    // Implement the returnBook method here
    @Override
    public ReturnDto returnBook(String firstName, String lastName, String bookTitle, Integer rentId, LocalDate returnDate) {
        ReadersInfo readerInfo = readerInfoRepository.findReadersinfoByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ExpressionException("Reader is not exist with given name: " + firstName + " " + lastName));

        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new ExpressionException("Book is not exist with given title: " + bookTitle));

        Rent rent = rentRepository.findById(new RentId(rentId, book.getId(), readerInfo.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Rent is not found with the given id"));

        System.out.println(rent.getId().getRentID());

        ReturnBook savedReturnBook = null;

            ReturnBook returnbook = new ReturnBook();

            ReturnBookId returnbookId = new ReturnBookId();
            returnbookId.setBookID(book.getId());
            returnbookId.setReaderID(readerInfo.getId());

            returnbook.setId(returnbookId);
            returnbook.setBookID(book);
            returnbook.setReaderID(readerInfo);
            returnbook.setReturnDate(returnDate);
            returnbook.setRentID(rent.getId().getRentID());

            savedReturnBook = returnBookRepository.save(returnbook);
            rentRepository.delete(rent);

            book.setAvailable(book.getAvailable() + 1);
            bookRepository.save(book);


        return ReturnMapper.mapToReturnDto(new Object[]{
                readerInfo.getId(),
                book.getId(),
                savedReturnBook.getRentID(),
                readerInfo.getFirstName(),
                readerInfo.getLastName(),
                book.getTitle(),
                savedReturnBook.getReturnDate()
        });
    }

    // Implement the countReturnBookByDate method here
    @Override
    public Integer countReturnBookByDate() {
        return returnBookRepository.countReturnBookByDate(LocalDate.now()).
                orElseThrow(() -> new ResourceNotFoundException("There is no book reserved today"));
    }
}