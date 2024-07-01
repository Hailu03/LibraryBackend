package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.AdminNotificationDto;
import com.library.programmingexercise.dto.LendDto;
import com.library.programmingexercise.dto.ReaderNotificationDto;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.ReaderNotification;
import com.library.programmingexercise.entity.ReadersInfo;
import com.library.programmingexercise.entity.RentRecord;
import com.library.programmingexercise.mapper.ReaderNotificationMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.repository.ReaderInfoRepository;
import com.library.programmingexercise.repository.ReaderNotificationRepository;
import com.library.programmingexercise.repository.RentrecordRepository;
import com.library.programmingexercise.service.ReaderNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderNotificationServiceImpl implements ReaderNotificationService {
    @Autowired
    private ReaderNotificationRepository readerNotificationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderInfoRepository readerInfoRepository;
    @Autowired
    private RentrecordRepository rentrecordRepository;
    // Implement the addReaderNotification method here
    @Override
    public ReaderNotificationDto addReaderNotification(ReaderNotificationDto readerNotificationDto) {
        ReaderNotification readerNotification = new ReaderNotification(
                readerNotificationDto.getNotificationID(),
                readerNotificationDto.getReaderID(),
                readerNotificationDto.getBookID(),
                readerNotificationDto.getMessage(),
                readerNotificationDto.getDateTime(),
                readerNotificationDto.isRead()
        );
        readerNotificationRepository.save(readerNotification);
        return readerNotificationDto;
    }

    // Implement the getReaderNotifications method here
    @Override
    public List<ReaderNotificationDto> getReaderNotifications(int readerID) {
        List<ReaderNotification> readerNotifications = readerNotificationRepository.findNotificationByReaderID(readerID);
        return ReaderNotificationMapper.mapToReaderNotificationDtoList(readerNotifications);
    }

    // Implement the markAsRead method here
    @Override
    public void markAsRead(int notificationID) {
        ReaderNotification readerNotification = readerNotificationRepository.findById(notificationID).orElse(null);
        if (readerNotification != null) {
            readerNotification.setRead(true);
            readerNotificationRepository.save(readerNotification);
        }
    }

    @Override
    public List<AdminNotificationDto> getReaderInfoAndBookDetails() {
        List<Object[]> results = readerNotificationRepository.findReaderInfoAndBookDetails();
        return results.stream()
                .map(result -> new AdminNotificationDto(
                        (Integer) result[0],      // readerID
                        (String) result[2],       // message
                        ((Timestamp) result[1]).toLocalDateTime(),
                        (String) result[3],       // firstName
                        (String) result[4],       // lastName
                        (String) result[5]        // bookTitle
                ))
                .collect(Collectors.toList());
    }
}
