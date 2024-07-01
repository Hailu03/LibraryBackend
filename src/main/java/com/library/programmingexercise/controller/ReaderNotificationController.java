package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.BorrowedBookDto;
import com.library.programmingexercise.dto.ReaderNotificationDto;
import com.library.programmingexercise.dto.AdminNotificationDto;
import com.library.programmingexercise.service.ReaderNotificationService;
import com.library.programmingexercise.service.RentService;
import com.library.programmingexercise.service.impl.RentServiceImpl;
import lombok.AllArgsConstructor;
import com.library.programmingexercise.dto.LendDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reader-notifications")
public class ReaderNotificationController {
    private final RentServiceImpl rentServiceImpl;
    private ReaderNotificationService readerNotificationService; // ReaderNotificationService object
    private RentService rentService; // RentService object
    // API for adding a reader notification
    @PostMapping
    public ReaderNotificationDto addReaderNotification(@RequestBody ReaderNotificationDto readerNotificationDto) {
        return readerNotificationService.addReaderNotification(readerNotificationDto);
    }

    // API for getting reader notifications by reader ID
    @GetMapping("/{readerID}")
    public List<ReaderNotificationDto> getReaderNotifications(@PathVariable int readerID) {
        return readerNotificationService.getReaderNotifications(readerID);
    }
    // API for marking a notification as read
    @PutMapping("/{notificationID}")
    public void markAsRead(@PathVariable int notificationID) {
        readerNotificationService.markAsRead(notificationID);
    }


    //get borrowed details
    @GetMapping("/details")
    public ResponseEntity<List<AdminNotificationDto>> getReaderInfoAndBookDetails(){
        List<AdminNotificationDto> details = readerNotificationService.getReaderInfoAndBookDetails();
        return ResponseEntity.ok(details);
    }

}
