package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.ReturnDto;
import com.library.programmingexercise.service.ReturnService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/return")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    // API for returning a book
    @PostMapping("/return-book")
    public ResponseEntity<ReturnDto> returnBook(@RequestParam String firstName,
                                                @RequestParam String lastName,
                                                @RequestParam String bookTitle,
                                                @RequestParam Integer rentId,
                                                @RequestParam LocalDate returnDate) {
        ReturnDto returnDto = returnService.returnBook(firstName, lastName, bookTitle, rentId,returnDate);
        return ResponseEntity.ok(returnDto);
    }

    // API for counting the number of books returned by date
    @GetMapping("/count-return-book")
    public ResponseEntity<Integer> countReturnBookByDate() {
        int count = returnService.countReturnBookByDate();
        return ResponseEntity.ok(count);
    }
}