package com.library.programmingexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderNotificationDto {
    private Integer notificationID;

    @JsonProperty("readerID")
    private Integer readerID;

    @JsonProperty("bookID")
    private Integer bookID;

    @JsonProperty("message")
    private String message;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("isRead")
    private boolean isRead;
}
