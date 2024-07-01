package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.ReaderNotificationDto;
import com.library.programmingexercise.entity.ReaderNotification;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderNotificationMapper {
    public static List<ReaderNotificationDto> mapToReaderNotificationDtoList(List<ReaderNotification> readerNotifications){
        return readerNotifications.stream()
                .map(readerNotification -> new ReaderNotificationDto(
                        readerNotification.getNotificationID(),
                        readerNotification.getReaderID(),
                        readerNotification.getBookID(),
                        readerNotification.getMessage(),
                        readerNotification.getDateTime(),
                        readerNotification.isRead()
                ))
                .collect(Collectors.toList());
    }
}
