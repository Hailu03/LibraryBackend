package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.ReviewBookDto;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.ReadersInfo;
import com.library.programmingexercise.entity.Reviewbook;
import com.library.programmingexercise.mapper.ReturnMapper;
import com.library.programmingexercise.mapper.ReviewBookMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.repository.ReaderInfoRepository;
import com.library.programmingexercise.repository.ReviewBookRepository;
import com.library.programmingexercise.service.ReviewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReviewBookServiceImpl implements ReviewBookService {
    @Autowired
    private ReviewBookRepository reviewBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderInfoRepository readersInfoRepository;

    // Implement the reviewBook method here
    @Override
    public ReviewBookDto reviewBook(LocalDate date, int rating, String review, int bookID, int readerID) {
        Book book = bookRepository.findById(bookID).orElseThrow(() -> new RuntimeException("Book not found"));
        ReadersInfo reader = readersInfoRepository.findById(readerID).orElseThrow(() -> new RuntimeException("Reader not found"));

        Optional<Reviewbook> reviewBook = reviewBookRepository.findByBookIDAndReaderID(book, reader);
        if (reviewBook.isPresent()) {
            reviewBook.get().setDate(date);
            reviewBook.get().setRating(rating);
            reviewBook.get().setReviewText(review);
            reviewBookRepository.save(reviewBook.get());
        } else {
            Reviewbook newReview = new Reviewbook();
            newReview.setBookID(book);
            newReview.setReaderID(reader);
            newReview.setDate(date);
            newReview.setRating(rating);
            newReview.setReviewText(review);
            reviewBookRepository.save(newReview);
        }

        return ReviewBookMapper.mapToReviewDto(new Object[]{
                reviewBook.get().getDate(),
                reviewBook.get().getRating(),
                reviewBook.get().getReviewText(),
                reviewBook.get().getBookID().getId(),
                reviewBook.get().getReaderID().getId()
        });
    }
}

