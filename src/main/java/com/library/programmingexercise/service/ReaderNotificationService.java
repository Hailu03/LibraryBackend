package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.LendDto;
import com.library.programmingexercise.dto.ReaderNotificationDto;
import com.library.programmingexercise.dto.AdminNotificationDto;
import java.time.LocalDate;
import java.util.List;

// an interface for the ReaderNotificationService
public interface ReaderNotificationService {
    List<ReaderNotificationDto> getReaderNotifications(int readerID);

    ReaderNotificationDto addReaderNotification(ReaderNotificationDto readerNotificationDto);

    //add this function for ServiceImpl
    List<AdminNotificationDto> getReaderInfoAndBookDetails();

    void markAsRead(int notificationID);


}
