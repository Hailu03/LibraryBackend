package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.ReviewBookDto;
import com.library.programmingexercise.service.ReviewBookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/review-text")
public class ReviewController {
    @Autowired
    private ReviewBookService reviewBookService;

    // API for adding a review
    @PutMapping("/update-review")
    public ResponseEntity<ReviewBookDto> updateReview(@RequestParam LocalDate date,
                                                      @RequestParam int rating,
                                                      @RequestParam String review,
                                                      @RequestParam int bookID,
                                                      @RequestParam int readerID) {
        // Implement the method to update a review
        return ResponseEntity.ok(reviewBookService.reviewBook(date, rating, review, bookID, readerID));
    }
}
