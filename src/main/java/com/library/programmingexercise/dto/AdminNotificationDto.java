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
public class AdminNotificationDto {
    @JsonProperty("readerID")
    private Integer readerID;

    @JsonProperty("message")
    private String message;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("bookTitle")
    private String bookTitle;
}
