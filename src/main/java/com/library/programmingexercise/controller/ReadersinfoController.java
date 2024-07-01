package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.dto.ReadersinfoDto;
import com.library.programmingexercise.entity.ReadersInfo;
import com.library.programmingexercise.mapper.ReadersinfoMapper;
import com.library.programmingexercise.service.ReadersinfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.naming.AuthenticationException;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/readersinfo")
public class ReadersinfoController {
    private ReadersinfoService readersinfoService;

    // API for adding a reader
    @PostMapping
    public ResponseEntity<ReadersinfoDto> addReaderInfo(@RequestBody ReadersinfoDto readerInfoDto){
        ReadersinfoDto savedReaderInfo = readersinfoService.addReaderInfo(readerInfoDto);
        return new ResponseEntity<>(savedReaderInfo, HttpStatus.CREATED);
    }

    // API for getting a reader by email
    @GetMapping("/{email}")
    public ResponseEntity<ReadersinfoDto> getReadersinfoByEmail(@PathVariable("email") String email) {
        ReadersinfoDto readersinfoDto = readersinfoService.findByEmail(email);
        return new ResponseEntity<>(readersinfoDto, HttpStatus.OK);
    }

    // API for authenticating a reader
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody ReadersinfoDto loginRequest) {
        try {
            // Perform authentication logic
            ReadersinfoDto readersinfoDto = readersinfoService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(readersinfoDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

    // API for updating a reader by ID
    @PutMapping("/{readerID}")
    public ResponseEntity<ReadersinfoDto> updateReadersinfo(@PathVariable("readerID") int readerID, @RequestBody ReadersinfoDto readersinfoDto) {
        ReadersInfo readersinfo = ReadersinfoMapper.maptoReadersinfo(readersinfoDto);
        System.out.println(readersinfo.getImage());
        System.out.println(readersinfoDto.getImage());
        ReadersinfoDto updatedReadersinfo = readersinfoService.updateReadersinfo(readerID, readersinfo);
        return new ResponseEntity<>(updatedReadersinfo, HttpStatus.OK);
    }

    // API for getting all readers
    @GetMapping
    public ResponseEntity<List<ReadersinfoDto>> getAllUsers() {
        List<ReadersinfoDto> users = readersinfoService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // API for getting top 5 most borrowing users
    @GetMapping("/top5")
    public ResponseEntity<List<ReadersinfoDto>> getTop5MostBorrowUser() {
        List<ReadersinfoDto> users = readersinfoService.getTop5MostBorrowUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/number-of-time-user-borrow/{id}")
    public Integer getNumberOfTimeUserBorrowBook(@PathVariable("id") Integer id){
        Integer numberOfTimeUserBorrowBook = readersinfoService.findNumberOfTimeUserBorrowById(id);
        return numberOfTimeUserBorrowBook;
    }

    // API for confirming email
    @GetMapping("/confirm-email/{email}")
    public ResponseEntity<?> confirmEmail(@PathVariable("email") String email) {
        boolean isEmailConfirmed = readersinfoService.confirmEmail(email);
        return ResponseEntity.ok(isEmailConfirmed);
    }

    // API for updating password
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody ReadersinfoDto readersinfoDto) {
        try {
            String email = readersinfoDto.getEmail();
            String newPassword = readersinfoDto.getPassword();
            readersinfoService.updatePassword(email, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating password");
        }
    }
}